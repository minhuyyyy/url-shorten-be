package com.huyttm.url_shortener.url_shortener.url;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface URLRepo extends JpaRepository<URL, Integer> {
    Optional<URL> findByOriginalURLAndShortenedURL(String originalURL, String shortCode);

    Optional<URL> findByShortenedURL(String shortcode);
}
