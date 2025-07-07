package com.jobportal.notification_service.controller;

import com.jobportal.notification_service.entity.Notification;
import com.jobportal.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> sendNotification(
            @RequestParam Long userId,
            @RequestParam String message) {
        Notification notification = notificationService.sendNotification(userId, message);
        return ResponseEntity.ok(notification);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }
}

