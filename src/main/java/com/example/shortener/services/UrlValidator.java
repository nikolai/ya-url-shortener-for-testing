package com.example.shortener.services;


import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class UrlValidator {

    String validateAndGetError(String url) {
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
        try {
            HttpResponse<Void> response = client.send(
                    HttpRequest.newBuilder(new URI(url)).build(),
                    HttpResponse.BodyHandlers.discarding());
            int responseCode = response.statusCode();

            if (responseCode >= HttpServletResponse.SC_BAD_REQUEST) {
                return String.format("invalid response http code %d from url %s",responseCode, url);
            }
        } catch (IOException | URISyntaxException | InterruptedException e) {
            return String.format("error occurred %s while sending request to %s", e, url);
        }

        return null;
    }

}
