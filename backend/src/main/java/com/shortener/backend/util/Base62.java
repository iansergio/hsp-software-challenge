package com.shortener.backend.util;

public class Base62 {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public static String encode(long input) {
        if (input == 0) {
            return String.valueOf(ALPHABET.charAt(0));
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (input > 0) {
            sb.append(ALPHABET.charAt((int) (input % BASE)));
            input /= BASE;
        }
        
        return sb.reverse().toString();
    }
}
