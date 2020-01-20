package com.example.shortener.model;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomKeyGen {
    private char[] dictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890".toCharArray();

    public String generateKey(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(dictionary[ThreadLocalRandom.current().nextInt(dictionary.length)]);
        }
        return sb.toString();
    }
}
