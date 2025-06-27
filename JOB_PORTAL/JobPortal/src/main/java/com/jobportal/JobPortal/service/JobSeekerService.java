package com.jobportal.JobPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.dto.JobSeekerDTO;
import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;


@Service
public class JobSeekerService {

    
    private final JobSeekerRepository jobSeekerRepository;
    private final UserService userService;
    private final ApplicationRepository applicationRepository;
    
    //to log JS activities in seeker_activity table
    @Autowired
    private SeekerActivityService seekerActivityService;
        

    public JobSeekerService(JobSeekerRepository jobSeekerRepository, UserService userService,
			ApplicationRepository applicationRepository, SeekerActivityService seekerActivityService) {
		super();
		this.jobSeekerRepository = jobSeekerRepository;
		this.userService = userService;
		this.applicationRepository = applicationRepository;
		this.seekerActivityService = seekerActivityService;
	}

	// ------------------------- Registers a Job Seeker ------------------
    /*A new User row is created with encrypted password and role "JOB_SEEKER".
     A new JobSeeker row is created and linked to that user through a foreign key.*/
    public JobSeeker registerJobSeeker(JobSeeker jobSeeker) {
    	//as every JS is associated with a user account, first get the user obj. embedded insidde the job seekr object sent in the request
        User user = jobSeeker.getUser();
        user.setRole("JOB_SEEKER");  //set the role to JS
        //register the user account
        user = userService.signUp(user); // called the sign up method user service and saves the user record to the DB
        //now set the saved user back into the JS obj.
        jobSeeker.setUser(user);
        //save JS to DB
        return jobSeekerRepository.save(jobSeeker);
    }
    
 //--------------------------- Get logged in  job seeker by username ----------------  
    public JobSeekerDTO getJobSeekerByUsername(String username) {
    	//call to fetch the JS with provided username
        JobSeeker jobSeeker = jobSeekerRepository.getJobSeekerByUsername(username);
        
        // If no record found, throw error
        if (jobSeeker == null) {
            throw new ResourceNotFoundException("Job Seeker not found with username: " + username);
        }
        //now as i ahve used the dto, convert th eJS entity to JD DTo
        return JobSeekerDTO.converttoDto(jobSeeker);
    }
    
    
//------------------------ Update job seeker ----------------------
/*this method Fetches the existing Job Seeker using ID
 *Updates only the provided fields (if not null), Saves the updated entity
 * Logs the update in the activity table, Returns the updated Job Seeker object*/
public JobSeeker updateJobSeeker(int jobSeekerId, JobSeeker updated) {
	//first fetch the existing job seeker record from DB
        JobSeeker existing = jobSeekerRepository.findById(jobSeekerId)
        		//if does not exits trrow exceeption
                .orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));
        //if updated name is not null, then update existing objedts field name
        if (updated.getName() != null)
        	existing.setName(updated.getName());
		if (updated.getEducation() != null)
			existing.setEducation(updated.getEducation());
		if (updated.getSkills() != null)
			existing.setSkills(updated.getSkills());
		if (updated.getExperience() != null)
			existing.setExperience(updated.getExperience());

		//savethe updated record back to DB
        JobSeeker saved = jobSeekerRepository.save(existing); //saved holds the latest state of the Job Seeker.

        // Log activity
        seekerActivityService.logActivity(saved, ActivityType.PROFILE_UPDATED, "Updated job seeker profile");

        return jobSeekerRepository.save(existing); //return the updated and saved JS 
    }

//=======================================================================================================================
//---------------------------------------------- FOR EX-----------------------------------------
    //---------------- Get a Job Seeker by ID -----------------------    
   public JobSeekerDTO getJobSeekerById(int jobSeekerId) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));
        return JobSeekerDTO.converttoDto(jobSeeker);
    }
    
    //--------------------------- get job seeker by application ID ----------------  

    public JobSeekerDTO getJobSeekerByAppId(int applicationId) {
    	applicationRepository.findById(applicationId)
               	.orElseThrow(() -> new ResourceNotFoundException("Application Id Not Found "));
		JobSeeker list = jobSeekerRepository.getJobSeekerByAppId(applicationId);

		return JobSeekerDTO.converttoDto(list);
	}
    

    //--------------------------- Deletes job seeker by ID ----------------  
    public void deleteJobSeeker(int jobSeekerId) {
        if (!jobSeekerRepository.existsById(jobSeekerId)) {
            throw new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId);
        }
        jobSeekerRepository.deleteById(jobSeekerId);
    }

    //--------------------------- Get all job seekers ----------------  
    public List<JobSeekerDTO> getAllJobSeekers() {
        List<JobSeeker> jobSeekers = jobSeekerRepository.findAll();
        return JobSeekerDTO.converttoDto(jobSeekers);
    }
    


}
