package com.huyttm.url_shortener.url_shortener.services;

import java.sql.SQLException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.huyttm.url_shortener.url_shortener.exceptions.ShortenedURLException;
import com.huyttm.url_shortener.url_shortener.models.URL;
import com.huyttm.url_shortener.url_shortener.respository.URLRepo;

@Service
public class URLServices {
    private final URLRepo urlRepo;

    public URLServices(URLRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public URL createUrl(URL url) {
        Optional<URL> foundURL = urlRepo.findByOriginalURLAndShortenedURL(url.getOriginalURL(),
                url.getShortenedURL());
        if (foundURL.isPresent()) {
            throw new ShortenedURLException("SHORTENED_URL_EXISTED", url.getOriginalURL(),
                    url.getShortenedURL());
        } else {
            return urlRepo.save(url);
        }
    }

    public Optional<URL> getURLByshortenedURL(String shortenedURL) {
        Optional<URL> foundURL = urlRepo.findByShortenedURL(shortenedURL);
        return foundURL;
    }
}
