package com.shortener.backend.controller;

import com.shortener.backend.model.Url;
import com.shortener.backend.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping
    public ResponseEntity<Url> shorten(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("url");
        Url shortUrl = urlService.generateShortUrl(originalUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String originalUrl = urlService.getAndTrackAccess(code);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(originalUrl));
        
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @GetMapping("/{code}/stats")
    public ResponseEntity<Url> getStats(@PathVariable String code) {
        Url url = urlService.getStats(code);
        return ResponseEntity.ok(url);
    }
}