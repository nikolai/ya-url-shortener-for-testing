package com.example.shortener.messages;

public class CreateRedirectResponse {
    private String shortUrl;
    private String secretKey;

    public CreateRedirectResponse(String shortUrl, String secretKey) {
        this.shortUrl = shortUrl;
        this.secretKey = secretKey;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
