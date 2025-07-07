package com.jobportal.application_service.service.impl;

import com.jobportal.application_service.client.JobClient;
import com.jobportal.application_service.client.NotificationClient;
import com.jobportal.application_service.client.UserClient;
import com.jobportal.application_service.dto.ApplicationRequest;
import com.jobportal.application_service.dto.ApplicationResponse;
import com.jobportal.application_service.entity.Application;
import com.jobportal.application_service.enums.ApplicationStatus;
import com.jobportal.application_service.exception.ApplicationNotFoundException;
import com.jobportal.application_service.exception.ApplicationServiceException;
import com.jobportal.application_service.exception.JobNotFoundException;
import com.jobportal.application_service.exception.UserNotFoundException;
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
    private final NotificationClient notificationClient;
    private final UserClient userClient;
    private final JobClient jobClient;

    @Override
    public ApplicationResponse apply(ApplicationRequest request) {
        // 👇 Validate User
        try {
            userClient.getUserById(request.getUserId());
        } catch (Exception ex) {
            throw  new UserNotFoundException(request.getUserId());
        }

        // 👇 Validate Job
        try {
            jobClient.getJobById(request.getJobId());
        } catch (Exception ex) {
            throw new JobNotFoundException(request.getJobId());
        }

        try {
            Application application = Application.builder()
                    .jobId(request.getJobId())
                    .userId(request.getUserId())
                    .resumeUrl(request.getResumeUrl())
                    .status(ApplicationStatus.PENDING)
                    .appliedAt(LocalDateTime.now())
                    .build();

            // Save to DB
            Application savedApplication = applicationRepository.save(application);

            // ✅ Send notification after saving successfully
            notificationClient.sendNotification(
                    savedApplication.getUserId(),
                    "You have successfully applied for job ID " + savedApplication.getJobId()
            );

            // Return response
            return mapToResponse(savedApplication);

        } catch (Exception e) {
            throw new ApplicationServiceException("Failed to apply for job. Please try again later.");
        }

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
                .orElseThrow(() -> new ApplicationNotFoundException(applicationId));

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

