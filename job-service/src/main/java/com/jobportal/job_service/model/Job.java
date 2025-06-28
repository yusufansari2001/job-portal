package com.jobportal.job_service.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String location;

    private Double minSalary;
    private Double maxSalary;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String skillsRequired;

    @Column(nullable = false)
    private Long postedBy; // Stores the user ID of the employer

    @Column(nullable = false)
    private LocalDateTime postingDate = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobStatus status = JobStatus.ACTIVE;

    // Enum for job status
    public enum JobStatus {
        ACTIVE,
        EXPIRED,
        CLOSED
    }
}

