package com.jobportal.application_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "job-service")  // must match spring.application.name of job service
public interface JobClient {

    @GetMapping("/jobs/{id}")
    Object getJobById(@PathVariable("id") Long jobId);
}

