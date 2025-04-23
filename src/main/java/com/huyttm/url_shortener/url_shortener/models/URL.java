package com.huyttm.url_shortener.url_shortener.models;

import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "urls")
public class URL {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Integer id;
        @NotEmpty
        String originalURL;
        @NotEmpty
        String shortenedURL;
        String password;
        @Column(name = "expiration_date")
        LocalDateTime expirationDate;
        @CreationTimestamp
        @Column(nullable = false, updatable = false)
        LocalDateTime createdAt;
        @UpdateTimestamp
        @Column(nullable = false)
        LocalDateTime updatedAt;

        public URL(@NotEmpty String originalURL, @NotEmpty String shortenedURL,
                        @NotEmpty LocalDateTime expirationDate, String password) {
                this.originalURL = originalURL;
                this.shortenedURL = shortenedURL;
                this.expirationDate = expirationDate;
                this.password = password;
        }

        public URL() {
        }

        public Integer getId() {
                return id;
        }

        public String getOriginalURL() {
                return originalURL;
        }

        public String getShortenedURL() {
                return shortenedURL;
        }

        public LocalDateTime getCreatedAt() {
                return createdAt;
        }

        public LocalDateTime getUpdatedAt() {
                return updatedAt;
        }

        public LocalDateTime getExpirationDate() {
                return expirationDate;
        }

        public String getPassword() {
                return password;
        }

        public void setOriginalURL(String originalURL) {
                this.originalURL = originalURL;
        }

        public void setShortenedURL(String shortenedURL) {
                this.shortenedURL = shortenedURL;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public void setExpirationDate(LocalDateTime expirationDate) {
                this.expirationDate = expirationDate;
        }

        

}
