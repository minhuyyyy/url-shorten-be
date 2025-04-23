package com.huyttm.url_shortener.url_shortener.dto;

import java.time.LocalDateTime;

public class UrlDTO {
    Integer id;
    String originalURL;
    String shortenedURL;
    LocalDateTime expirationDate;

    public UrlDTO() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public String getShortenedURL() {
        return shortenedURL;
    }

    public void setShortenedURL(String shortenedURL) {
        this.shortenedURL = shortenedURL;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }


}
