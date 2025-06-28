package com.jobportal.job_service.dto;

import com.jobportal.job_service.model.Job;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private Double minSalary;
    private Double maxSalary;
    private String companyName;
    private String skillsRequired;
    private Long postedBy;
    private LocalDateTime postingDate;
    private Job.JobStatus status;
}
