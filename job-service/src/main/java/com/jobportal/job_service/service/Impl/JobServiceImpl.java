package com.jobportal.job_service.service.Impl;

import com.jobportal.job_service.dto.JobRequest;
import com.jobportal.job_service.dto.JobResponse;
import com.jobportal.job_service.exception.ResourceNotFoundException;
import com.jobportal.job_service.exception.UnauthorizedAccessException;
import com.jobportal.job_service.model.Job;
import com.jobportal.job_service.repository.JobRepository;
import com.jobportal.job_service.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;

    @Override
    public JobResponse createJob(JobRequest jobRequest, Long postedBy) {
        Job job = mapToJob(jobRequest);
        job.setPostedBy(postedBy);
        Job savedJob = jobRepository.save(job);
        return mapToJobResponse(savedJob);
    }

    @Override
    public JobResponse getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));
        return mapToJobResponse(job);
    }

    @Override
    public List<JobResponse> getAllJobs() {
        return jobRepository.findAll().stream()
                .map(this::mapToJobResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<JobResponse> getJobsByEmployer(Long employerId) {
        return jobRepository.findByPostedBy(employerId).stream()
                .map(this::mapToJobResponse)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponse updateJob(Long id, JobRequest jobRequest) {
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found"));

        // Update fields
        existingJob.setTitle(jobRequest.getTitle());
        existingJob.setDescription(jobRequest.getDescription());
        existingJob.setLocation(jobRequest.getLocation());
        existingJob.setMinSalary(jobRequest.getMinSalary());
        existingJob.setMaxSalary(jobRequest.getMaxSalary());
        existingJob.setCompanyName(jobRequest.getCompanyName());
        existingJob.setSkillsRequired(jobRequest.getSkillsRequired());

        Job updatedJob = jobRepository.save(existingJob);
        return mapToJobResponse(updatedJob);
    }

    @Override
    public void deleteJob(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Job not found with id: " + id));
        jobRepository.delete(job);
    }

    private Job mapToJob(JobRequest jobRequest) {
        Job job = new Job();
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setLocation(jobRequest.getLocation());
        job.setMinSalary(jobRequest.getMinSalary());
        job.setMaxSalary(jobRequest.getMaxSalary());
        job.setCompanyName(jobRequest.getCompanyName());
        job.setSkillsRequired(jobRequest.getSkillsRequired());
        return job;
    }

    private JobResponse mapToJobResponse(Job job) {
        JobResponse response = new JobResponse();
        response.setId(job.getId());
        response.setTitle(job.getTitle());
        response.setDescription(job.getDescription());
        response.setLocation(job.getLocation());
        response.setMinSalary(job.getMinSalary());
        response.setMaxSalary(job.getMaxSalary());
        response.setCompanyName(job.getCompanyName());
        response.setSkillsRequired(job.getSkillsRequired());
        response.setPostedBy(job.getPostedBy());
        response.setPostingDate(job.getPostingDate());
        response.setStatus(job.getStatus());
        return response;
    }
}
