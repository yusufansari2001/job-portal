package com.jobportal.notification_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    @GetMapping("/test")
    public String testNotif() {
        return "✅ Notification service is working!";
    }
}
