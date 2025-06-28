package com.jobportal.job_service.service;

import com.jobportal.job_service.dto.JobRequest;
import com.jobportal.job_service.dto.JobResponse;

import java.util.List;

public interface JobService {
    JobResponse createJob(JobRequest jobRequest, Long postedBy);
    JobResponse getJobById(Long id);
    List<JobResponse> getAllJobs();
    List<JobResponse> getJobsByEmployer(Long employerId);
    JobResponse updateJob(Long id, JobRequest jobRequest);
    void deleteJob(Long id);
}
