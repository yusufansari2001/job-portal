package com.jobportal.job_service.repository;

import com.jobportal.job_service.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByPostedBy(Long userId);
    List<Job> findByStatus(Job.JobStatus status);
}
