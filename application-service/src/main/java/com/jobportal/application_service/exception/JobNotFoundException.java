package com.jobportal.application_service.exception;

public class JobNotFoundException extends ApplicationServiceException {
    public JobNotFoundException(Long jobId) {
        super("Job not found with ID: " + jobId);
    }
}


