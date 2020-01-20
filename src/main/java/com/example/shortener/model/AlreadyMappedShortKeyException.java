package com.example.shortener.model;

public class AlreadyMappedShortKeyException extends ModelException {
    public AlreadyMappedShortKeyException(String key) {
        super(key, null, true, false);
    }
}
