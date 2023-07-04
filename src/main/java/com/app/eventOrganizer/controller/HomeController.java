package com.app.eventOrganizer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String home() {

        return "home";
    }
}