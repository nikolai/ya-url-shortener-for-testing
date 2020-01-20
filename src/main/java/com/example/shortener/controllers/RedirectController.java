package com.example.shortener.controllers;

import com.example.shortener.model.Redirection;
import com.example.shortener.model.RedirectionNotFoundException;
import com.example.shortener.services.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

@Controller
public class RedirectController {

    @Autowired
    UrlShortenerService shortener;

    @RequestMapping("/{shortKey}")
    public void doRedirect(@PathVariable String shortKey, HttpServletResponse response) {
        Redirection redirection = shortener.resolve(shortKey);
        response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        response.setHeader("Location", redirection.getLongUrl());
    }

    @ExceptionHandler(RedirectionNotFoundException.class)
    void handle(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
            bw.write("oh no");
        }
    }
}
