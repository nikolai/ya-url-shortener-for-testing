package com.example.shortener.messages;

import java.util.Map;

public class ConfigChangedRequest {

    private Map<String, String> configs;


    public Map<String, String> getConfigs() {
        return configs;
    }
}
