package com.example.shortener.controllers;

import com.example.shortener.messages.AdminGetStatsResponse;
import com.example.shortener.model.Redirection;
import com.example.shortener.services.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController extends BaseController {

    @Autowired
    UrlShortenerService service;

    @RequestMapping(value = "admin/{secretKey}", method = RequestMethod.GET)
    public AdminGetStatsResponse getStats(@PathVariable String secretKey) {
        Redirection redirection = service.findBySecretKey(secretKey);
        return new AdminGetStatsResponse(redirection.getCreationDate(), redirection.getUsageCount());
    }

    @RequestMapping(value = "admin/{secretKey}", method = RequestMethod.DELETE)
    public void deleteRedirection(@PathVariable String secretKey) {
        service.deleteRedirection(secretKey);
    }

}
