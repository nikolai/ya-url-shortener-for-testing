package com.example.shortener.services;

import com.example.shortener.messages.CreateRedirectResponse;
import com.example.shortener.model.RandomKeyGen;
import com.example.shortener.model.Redirection;
import com.example.shortener.model.RedirectionNotFoundException;
import com.example.shortener.model.RedirectionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortenerService {
    @Autowired
    private RedirectionRepo repo;
    @Autowired
    private RandomKeyGen gen;

    private int shortKeySize = 3;
    private String appUrl = "http://localhost:8080";


    public String shorten(String longUrl) {
        String shortKey = gen.generateKey(shortKeySize);
        repo.save(new Redirection(longUrl, shortKey));
        return appUrl + "/" + shortKey;
    }

    public Redirection resolve(String shortKey) throws RedirectionNotFoundException {
        Optional<Redirection> redirection = repo.findById(shortKey);
        if (redirection.isPresent()) {
            return redirection.get();
        }
        throw new RedirectionNotFoundException(shortKey);
    }
}
