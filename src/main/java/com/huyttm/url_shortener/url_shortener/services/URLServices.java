package com.huyttm.url_shortener.url_shortener.services;

import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.huyttm.url_shortener.url_shortener.dto.UrlDTO;
import com.huyttm.url_shortener.url_shortener.exceptions.ShortenedURLException;
import com.huyttm.url_shortener.url_shortener.models.URL;
import com.huyttm.url_shortener.url_shortener.respository.URLRepo;

@Service
public class URLServices {
    private final URLRepo urlRepo;
    private final ModelMapper modelMapper;



    public URLServices(URLRepo urlRepo, ModelMapper modelMapper) {
        this.urlRepo = urlRepo;
        this.modelMapper = modelMapper;
    }

    public UrlDTO createUrl(URL url) {
        Optional<URL> foundURL = urlRepo.findByOriginalURLAndShortenedURL(url.getOriginalURL(),
                url.getShortenedURL());
        if (foundURL.isPresent()) {
            throw new ShortenedURLException("SHORTENED_URL_EXISTED", url.getOriginalURL(),
                    url.getShortenedURL());
        } else {
            URL savedUrl = urlRepo.save(url); // Save the entity
            return modelMapper.map(savedUrl, UrlDTO.class); // Map AFTER saving

        }
    }

    public Optional<URL> getURLByshortenedURL(String shortenedURL) {
        Optional<URL> foundURL = urlRepo.findByShortenedURL(shortenedURL);
        return foundURL;
    }
}
