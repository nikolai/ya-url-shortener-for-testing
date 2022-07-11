package com.example.shortener.services;

import com.example.shortener.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortenerService {

    @Autowired
    private RedirectionRepo repo;
    @Autowired
    private RandomKeyGen gen;

    @Value("${shortKeySize}")
    private Integer shortKeySize = 3;

    @Value("${adminKeySize}")
    private Integer adminKeySize = 10;

    @Value("${application.domain}")
    private String appDomain = "localhost";

    @Value("${application.protocol}")
    private String protocol = "http";

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    UrlValidator validator;

    public Pair<String, String> shorten(String longUrl) {
        String validationError = validator.validateAndGetError(longUrl);
        if (validationError != null) {
            throw new InvalidUrlException(validationError);
        }
        String shortKey = gen.generateKey(shortKeySize);
        String secretKey = gen.generateKey(adminKeySize);

        Redirection redirection = new Redirection(longUrl, shortKey, secretKey);
        repo.save(redirection);

        return Pair.of(formatShortUrl(shortKey), secretKey);
    }

    private String formatShortUrl(String tail) {
        return protocol + "://" + appDomain + ":" + serverPort + "/" + tail;
    }

    public Redirection resolve(String shortKey) throws RedirectionNotFoundException {
        Optional<Redirection> redirectionO = repo.findByShortKey(shortKey);
        if (redirectionO.isPresent()) {
            Redirection redirection = redirectionO.get();
            redirection.incrementUsageCount();
            repo.save(redirection);
            return redirection;
        }
        throw new RedirectionNotFoundException(shortKey);
    }

    public Redirection findBySecretKey(String secretKey) {
        Optional<Redirection> redirectionO = repo.findBySecretKey(secretKey);
        if (redirectionO.isPresent()) {
            return redirectionO.get();
        }
        throw new RedirectionNotFoundException(secretKey);
    }

    public void deleteRedirection(String secretKey) {
        repo.delete(findBySecretKey(secretKey));
    }
}
