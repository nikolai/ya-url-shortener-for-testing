package com.example.shortener.messages;

public class CreateRedirectResponse {
    private String shortUrl;

    public CreateRedirectResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }
}
