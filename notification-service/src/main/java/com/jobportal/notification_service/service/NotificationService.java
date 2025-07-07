package com.jobportal.notification_service.service;

import com.jobportal.notification_service.entity.Notification;

import java.util.List;

public interface NotificationService {

    Notification sendNotification(Long userId, String message);

    List<Notification> getUserNotifications(Long userId);
}

