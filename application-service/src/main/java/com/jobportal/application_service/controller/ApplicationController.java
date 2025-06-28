package com.jobportal.application_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/applications")
public class ApplicationController {
    @GetMapping("/test")
    public String testApp() {
        return "✅ Application service is working!";
    }
}
