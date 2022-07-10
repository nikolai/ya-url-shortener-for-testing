package com.example.shortener.messages;

public class CreateRedirectRequest {
    private String longUrl;

    public CreateRedirectRequest() {
    }

    public CreateRedirectRequest(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }
}
