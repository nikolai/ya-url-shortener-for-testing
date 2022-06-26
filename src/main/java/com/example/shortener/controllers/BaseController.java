package com.example.shortener.controllers;

import com.example.shortener.model.RedirectionNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BaseController {
    @ExceptionHandler(RedirectionNotFoundException.class)
    void handle(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
            bw.write("oh no");
        }
    }
}
