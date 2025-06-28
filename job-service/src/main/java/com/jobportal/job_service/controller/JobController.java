package com.jobportal.job_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobController {
    @GetMapping("/test")
    public String testJob() {
        return "✅ Job service is working!";
    }
}
