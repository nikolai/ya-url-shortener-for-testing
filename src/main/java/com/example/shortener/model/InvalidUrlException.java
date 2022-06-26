package com.example.shortener.model;

public class InvalidUrlException extends ModelException {
    public InvalidUrlException(String request) {
        super(request);
    }
}
