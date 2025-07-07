package com.jobportal.application_service.controller;

import com.jobportal.application_service.dto.ApplicationRequest;
import com.jobportal.application_service.dto.ApplicationResponse;
import com.jobportal.application_service.service.ApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @GetMapping("/test")
    public String testApp() {
        return "✅ Application service is working!";
    }

    @PostMapping
    public ResponseEntity<ApplicationResponse> applyToJob(
            @RequestBody @Valid ApplicationRequest request) {
        ApplicationResponse response = applicationService.apply(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ApplicationResponse>> getByUser(
            @PathVariable Long userId) {
        return ResponseEntity.ok(applicationService.getApplicationsByUser(userId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<ApplicationResponse>> getByJob(
            @PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<ApplicationResponse> updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        return ResponseEntity.ok(applicationService.updateStatus(id, status));
    }
}

