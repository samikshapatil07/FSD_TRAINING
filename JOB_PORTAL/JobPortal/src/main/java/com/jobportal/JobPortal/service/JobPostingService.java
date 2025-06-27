package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.dto.JobPostingDTO;
import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.repository.HrRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//implemented paging for get all jobs
//implemented batch insert for posting job  
@Service
public class JobPostingService {

    @Autowired
    private JobPostingRepository jobPostingRepository;
        
    @Autowired
    private HrRepository hrRepository;
    
    Logger logger = LoggerFactory.getLogger("JobPostingService");

    //implemented batch insert for posting job   
    /* we use batch insert when HR wants to post multiple job listings at once
     * used when we want to save multiple records at once */
    
  //--------------------- Posts a new Job -------------------------------------------------------
	/*hr posts a new job, used the hr username to fect their details then associate the hr with the job before saving*/
    public JobPosting postJobs(JobPosting jobPosting, String username) {
		// TODO Auto-generated method stub
		Hr hr = hrRepository.getHrByUsername(username);//fetch hr by username
        logger.info("Hr record fetched by username");
        jobPosting.setHr(hr); //link tht hr to job posting
        logger.info("Adding.. Hr to Database");
        
        return jobPostingRepository.save(jobPosting);//save to database
	}
   
//--------------------- Get all Job Postings --------------------------------------------
    //implemeting paging in react not in api

	public List<JobPostingDTO> getAllJobs() {

        List<JobPosting> jobPostings = jobPostingRepository.findAll();
        return JobPostingDTO.converttoDto(jobPostings);
    }
	
  //--------------------- update a Job by its ID --------------------------------------------------
	/*allows hr to update an existing job by its id
	 * first check if the job existes and belongs to the hr
	 * then update the fields..*/
	public JobPosting updateJob(int jobId, JobPosting updatedJob,Hr hr) {
	    JobPosting existing = jobPostingRepository.findById(jobId)
	            .orElseThrow(() -> new ResourceNotFoundException("Job not found with ID: " + jobId));

	    //check if the logged in hr has posted this job
	    if (existing.getHr().getId() != hr.getId()) {
	        throw new ResourceNotFoundException("You are not authorized to update this job posting.");
	    }

	    //update fields
	    if (updatedJob.getJobTitle() != null)
	        existing.setJobTitle(updatedJob.getJobTitle());
	    if (updatedJob.getDescription() != null)
	        existing.setDescription(updatedJob.getDescription());
	    if (updatedJob.getSkills() != null)
	        existing.setSkills(updatedJob.getSkills());
	    if (updatedJob.getLocation() != null)
	        existing.setLocation(updatedJob.getLocation());
	    if (updatedJob.getSalary() != 0)
	        existing.setSalary(updatedJob.getSalary());
	    if (updatedJob.getDepartment() != null)
	        existing.setDepartment(updatedJob.getDepartment());
	    if (updatedJob.getCompany() != null)
	        existing.setCompany(updatedJob.getCompany());
	    if (updatedJob.getExperience() != null)
	        existing.setExperience(updatedJob.getExperience());
	    
	    //return  the updated job
	    return jobPostingRepository.save(existing);
	}
	
    
  //--------------------- Search Jobs ------------------------------------------------------------
   /**/
	public List<JobPostingDTO> searchJobs(String job_title, String location, String company) {
        
        List<JobPosting> jobPostings = jobPostingRepository.searchJobs(job_title, location, company);
        return JobPostingDTO.converttoDto(jobPostings);
    }
    //--------------------- get job by hr ------------------------------------------------------------
/*return all job postings created by  hr, udentify by their username*/
	public List<JobPosting> getJobsByHr(String username) {
		// TODO Auto-generated method stub
		return jobPostingRepository.getJobsByHr(username);
	}
	   
 //--------------------- Delete a Job by ID ------------------------------------------------
    public void deleteJob(int jobId) {
        if (!jobPostingRepository.existsById(jobId)) {
            throw new ResourceNotFoundException("Job posting not found with ID: " + jobId);
        }
        jobPostingRepository.deleteById(jobId);
    }
   
    
    
    
 //===================================================================================
  //--------------------- Get a Job by ID ------------------------------------------------------
    public JobPostingDTO getJobById(int jobId) {
        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                    .orElseThrow(() -> new ResourceNotFoundException("Job Id Not Found "));
        
        return JobPostingDTO.converttoDto(jobPosting);
    }


}
