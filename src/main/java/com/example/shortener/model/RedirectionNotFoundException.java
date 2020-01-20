package com.example.shortener.model;

public class RedirectionNotFoundException extends ModelException {
    public RedirectionNotFoundException(String request) {
        super(request);
    }
}
