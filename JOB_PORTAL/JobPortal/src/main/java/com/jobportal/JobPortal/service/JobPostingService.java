package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.repository.HrRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//implemented paging for get all jobs
//implemented batch insert for posting job  
@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;
    
    @Autowired
    private HrRepository hrRepository;

    //implemented batch insert for posting job   
    /* we use batch insert when HR wants to post multiple job listings at once
     * used when we want to save multiple records at once */
    
  //--------------------- Posts a new Job -------------------------------------------------------
    public List<JobPosting> batchPostJobs(List<JobPosting> jobList, int hrId) {
        Hr hr = hrRepository.findById(hrId)
                .orElseThrow(() -> new ResourceNotFoundException("HR not found with ID: " + hrId));

        for (JobPosting job : jobList) {
            job.setHr(hr);
            job.setCreatedAt(LocalDateTime.now());
        }

        return jobPostingRepository.saveAll(jobList);
    }
    
  //--------------------- update a Job by its ID --------------------------------------------------
    public JobPosting updateJob(int id, JobPosting updatedJob) {
        JobPosting existingJob = jobPostingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));

        existingJob.setJobTitle(updatedJob.getJobTitle());
        existingJob.setDescription(updatedJob.getDescription());
        existingJob.setSkills(updatedJob.getSkills());
        existingJob.setLocation(updatedJob.getLocation());
        existingJob.setSalary(updatedJob.getSalary());
        existingJob.setDepartment(updatedJob.getDepartment());
        existingJob.setCompany(updatedJob.getCompany());
        existingJob.setExperience(updatedJob.getExperience());

        return jobPostingRepository.save(existingJob);
    }
    
 //--------------------- Delete a Job by ID ------------------------------------------------
    public void deleteJob(int jobId) {
        if (!jobPostingRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("Job posting not found with ID: " + jobId);
        }
        jobPostingRepository.deleteById(jobId);
    }
    
  //--------------------- Get all Job Postings --------------------------------------------
    //implemeting paging

	public Page<JobPosting> getAllJobs(int page, int size) {
		//paging
        PageRequest pageable = PageRequest.of(page, size);
        return jobPostingRepository.findAll(pageable);
    }
	
  //--------------------- Get a Job by ID ------------------------------------------------------
    public JobPosting getJobById(int jobId) {
        return jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("Job posting not found with ID: " + jobId));
    }
    
  //--------------------- Search Jobs ------------------------------------------------------------
    public List<JobPosting> searchJobs(String jobTitle, String location, String company) {
        return jobPostingRepository.searchJobs(jobTitle, location, company);
    }

}
