package com.example.shortener.controllers;

import com.example.shortener.model.ErrorObject;
import com.example.shortener.model.RedirectionNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class BaseController {
    @ExceptionHandler(RedirectionNotFoundException.class)
    void handle(HttpServletResponse response, RedirectionNotFoundException exception) throws IOException {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        ObjectMapper mapper = new ObjectMapper();
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()))) {
            bw.write(mapper.writeValueAsString(new ErrorObject("Redirection not found by key " + exception.getMessage())));
        }
    }
}
