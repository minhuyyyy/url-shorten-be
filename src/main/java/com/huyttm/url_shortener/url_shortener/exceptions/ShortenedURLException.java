package com.huyttm.url_shortener.url_shortener.exceptions;

public class ShortenedURLException extends RuntimeException {
    private String originalURL;
    private String shortenedURL;


    public ShortenedURLException(String message, String originalURL, String shortenedURL) {
        super(message);
        this.originalURL = originalURL;
        this.shortenedURL = shortenedURL;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public String getShortenedURL() {
        return shortenedURL;
    }

}
