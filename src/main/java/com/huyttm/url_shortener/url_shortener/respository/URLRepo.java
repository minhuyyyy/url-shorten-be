package com.huyttm.url_shortener.url_shortener.respository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.huyttm.url_shortener.url_shortener.models.URL;

@Repository
public interface URLRepo extends JpaRepository<URL, Integer> {
    Optional<URL> findByOriginalURLAndShortenedURL(String originalURL, String shortCode);

    Optional<URL> findByShortenedURL(String shortcode);
}
