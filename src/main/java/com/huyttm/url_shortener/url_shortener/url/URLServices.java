package com.huyttm.url_shortener.url_shortener.url;

import java.sql.SQLException;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.huyttm.url_shortener.url_shortener.exceptions.ShortenedURLException;

@Service
public class URLServices {
    private final URLRepo urlRepo;

    public URLServices(URLRepo urlRepo) {
        this.urlRepo = urlRepo;
    }

    public URL createUrl(URL url) {
        Optional<URL> foundURL =
                urlRepo.findByOriginalURLAndShortenedURL(url.originalURL, url.shortenedURL);
        if (foundURL.isPresent()) {
            throw new ShortenedURLException("SHORTENED_URL_EXISTED", url.originalURL,
                    url.shortenedURL);
        } else {
            return urlRepo.save(url);
        }
    }

    public Optional<URL> getURLByshortenedURL(String shortenedURL) {
        Optional<URL> foundURL = urlRepo.findByShortenedURL(shortenedURL);
        return foundURL;
    }
}
