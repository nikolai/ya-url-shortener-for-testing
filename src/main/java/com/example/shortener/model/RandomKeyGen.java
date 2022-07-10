package com.example.shortener.model;

import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
public class RandomKeyGen {
    private final char[] dictionary = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890".toCharArray();

    public String generateKey(int size) {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            sb.append(dicChars()[ThreadLocalRandom.current().nextInt(dicChars().length)]);
        }
        return sb.toString();
    }

    private char[] dicChars() {
        return dictionary;
    }
}
