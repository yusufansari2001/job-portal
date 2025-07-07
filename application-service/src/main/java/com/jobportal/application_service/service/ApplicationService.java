package com.jobportal.application_service.service;

import com.jobportal.application_service.dto.ApplicationRequest;
import com.jobportal.application_service.dto.ApplicationResponse;

import java.util.List;

public interface ApplicationService {

    ApplicationResponse apply(ApplicationRequest request);

    List<ApplicationResponse> getApplicationsByUser(Long userId);

    List<ApplicationResponse> getApplicationsByJob(Long jobId);

    ApplicationResponse updateStatus(Long applicationId, String status);
}

