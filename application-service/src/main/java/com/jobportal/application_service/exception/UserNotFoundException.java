package com.jobportal.application_service.exception;

public class UserNotFoundException extends ApplicationServiceException {
    public UserNotFoundException(Long userId) {
        super("User not found with ID: " + userId);
    }
}

