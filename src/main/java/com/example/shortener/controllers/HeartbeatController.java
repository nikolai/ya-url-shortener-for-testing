package com.example.shortener.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HeartbeatController {
    @RequestMapping("/ping")
    public String ping() {
        return "it's ok, ts=" + new Date().getTime();
    }
}
