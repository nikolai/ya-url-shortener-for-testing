package com.example.shortener.model;

/**
 * @author ngsmirnov
 */
public class ErrorObject {
    private String error;

    public ErrorObject() {
    }

    public ErrorObject(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
