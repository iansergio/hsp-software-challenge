package com.shortener.backend.service;

import com.shortener.backend.model.Url;
import com.shortener.backend.repository.UrlRepository;
import com.shortener.backend.util.Base62;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public Url generateShortUrl(String originalUrl) {
        Url url = urlRepository.save(new Url(originalUrl));

        String code = Base62.encode(url.getId());
        url.setShortCode(code);

        return urlRepository.save(url);
    }

    @Transactional
    public String getAndTrackAccess(String shortCode) {
        Url url = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada"));

        long currentClicks = url.getClickCount() != null ? url.getClickCount() : 0L;
        url.setClickCount(currentClicks + 1);

        return url.getOriginalUrl();
    }

    public Url getStats(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL não encontrada"));
    }
}