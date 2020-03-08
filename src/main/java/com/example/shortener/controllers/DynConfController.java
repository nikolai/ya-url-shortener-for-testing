package com.example.shortener.controllers;

import com.example.shortener.messages.ConfigChangedRequest;
import com.example.shortener.services.DynConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DynConfController {

    @Autowired
    DynConfService service;

    @RequestMapping(value="/config", method = RequestMethod.POST)
    public void configChanged(@RequestBody ConfigChangedRequest request) {
        service.onConfigChanged(request.getConfigs());
    }

}
