package com.jobportal.application_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")  // must match spring.application.name of user service
public interface UserClient {

    @GetMapping("/users/{id}")
    Object getUserById(@PathVariable("id") Long userId);
}

