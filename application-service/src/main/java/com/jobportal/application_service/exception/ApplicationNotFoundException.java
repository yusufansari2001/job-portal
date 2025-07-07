package com.jobportal.application_service.exception;

public class ApplicationNotFoundException extends ApplicationServiceException {
    public ApplicationNotFoundException(Long id) {
        super("Application not found with ID: " + id);
    }
}

