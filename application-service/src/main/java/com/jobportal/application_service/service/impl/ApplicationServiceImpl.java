package com.jobportal.application_service.service.impl;

import com.jobportal.application_service.dto.ApplicationRequest;
import com.jobportal.application_service.dto.ApplicationResponse;
import com.jobportal.application_service.entity.Application;
import com.jobportal.application_service.enums.ApplicationStatus;
import com.jobportal.application_service.repository.ApplicationRepository;
import com.jobportal.application_service.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;

    @Override
    public ApplicationResponse apply(ApplicationRequest request) {
        Application application = Application.builder()
                .jobId(request.getJobId())
                .userId(request.getUserId())
                .resumeUrl(request.getResumeUrl())
                .status(ApplicationStatus.PENDING)
                .appliedAt(LocalDateTime.now())
                .build();

        Application saved = applicationRepository.save(application);
        return mapToResponse(saved);
    }

    @Override
    public List<ApplicationResponse> getApplicationsByUser(Long userId) {
        return applicationRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<ApplicationResponse> getApplicationsByJob(Long jobId) {
        return applicationRepository.findByJobId(jobId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApplicationResponse updateStatus(Long applicationId, String status) {
        Application application = applicationRepository.findById(applicationId)
                .orElseThrow(() -> new RuntimeException("Application not found"));

        application.setStatus(ApplicationStatus.valueOf(status.toUpperCase()));
        return mapToResponse(applicationRepository.save(application));
    }

    private ApplicationResponse mapToResponse(Application app) {
        return ApplicationResponse.builder()
                .id(app.getId())
                .jobId(app.getJobId())
                .userId(app.getUserId())
                .resumeUrl(app.getResumeUrl())
                .status(app.getStatus())
                .appliedAt(app.getAppliedAt())
                .build();
    }
}

