package com.jobportal.job_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class JobRequest {
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Location is required")
    private String location;

    @PositiveOrZero(message = "Salary must be positive")
    private Double minSalary;

    @PositiveOrZero(message = "Salary must be positive")
    private Double maxSalary;

    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotBlank(message = "Skills are required")
    private String skillsRequired;
}
