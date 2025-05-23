package com.huyttm.url_shortener.url_shortener.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.huyttm.url_shortener.url_shortener.dto.UrlDTO;
import com.huyttm.url_shortener.url_shortener.exceptions.ShortenedURLException;
import com.huyttm.url_shortener.url_shortener.models.URL;
import com.huyttm.url_shortener.url_shortener.services.URLServices;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/urls")
@CrossOrigin(origins = "http://localhost:3000")
public class URLController {
    private final URLServices urlServices;
    private final PasswordEncoder passwordEncoder;

    public URLController(URLServices urlServices, PasswordEncoder passwordEncoder) {
        this.urlServices = urlServices;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createURL(@Valid @RequestBody URL url) {
        try {
            if (url.getPassword() != null && !url.getPassword().isBlank()) {
                String hashedPassword = passwordEncoder.encode(url.getPassword());
                url.setPassword(hashedPassword); // Set the hashed password
            }
            UrlDTO createdUrl = urlServices.createUrl(url);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUrl);

        } catch (ShortenedURLException ex) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("error", ex.getMessage()));
        }
    }

    @GetMapping("/{shortcode}")
    public ResponseEntity<?> getURL(@PathVariable String shortcode) {
        Optional<URL> foundURL = urlServices.getURLByshortenedURL(shortcode);
        if (foundURL.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("Location", foundURL.get().getOriginalURL());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
