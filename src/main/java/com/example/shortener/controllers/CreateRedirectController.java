package com.example.shortener.controllers;

import com.example.shortener.messages.CreateRedirectRequest;
import com.example.shortener.messages.CreateRedirectResponse;
import com.example.shortener.services.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.*;

@RestController
public class CreateRedirectController {

    @Autowired
    UrlShortenerService shortener;

    @RequestMapping(value="/make_shorter", method = RequestMethod.POST)
    public CreateRedirectResponse createRedirect(@RequestBody CreateRedirectRequest request) {
        Pair<String, String> shortUrlAndSecret = shortener.shorten(request.getLongUrl());
        return new CreateRedirectResponse(shortUrlAndSecret.getFirst(), shortUrlAndSecret.getSecond());
    }

}
