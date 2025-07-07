package com.jobportal.application_service.dto;

import com.jobportal.application_service.enums.ApplicationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationResponse {

    private Long id;

    private Long jobId;

    private Long userId;

    private String resumeUrl;

    private ApplicationStatus status;

    private LocalDateTime appliedAt;
}

