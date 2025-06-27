package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.dto.JobSeekerDTO;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.service.JobSeekerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/*this class handles the 3 API call : 1.register JS
 *                                    2. get logged in JS
 *                                    3. update JS profile (else are for executive that i have not implemented)*/
@RestController
@CrossOrigin(origins = "http://localhost:5173") // Allows frontend on port 5173 to access APIs
@RequestMapping("/api/jobseeker")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService; // Injects the JobSeeker service for business logic
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("JobSeekerController");
 

// ------------------------- Register Job Seeker (JS)---------------------------------------------
    /* called in component/signup
     * AIM     : To register a new Job Seeker
     * PATH    : /api/jobseeker/register
     * METHOD  : POST
     * INPUT   : @RequestBody JobSeeker jobSeeker
     * RESPONSE: JobSeeker 
     */
    @PostMapping("/register")
    public JobSeeker registerJobSeeker(@RequestBody JobSeeker jobSeeker) {
        //logger
    	logger.info("Registering Job Seeker...");
        return jobSeekerService.registerJobSeeker(jobSeeker); // here call service to save the JS
    }


// ------------------------- get js by username (EX)---------------------------------------------
    /*
     * AIM     : To get js by username
     * PATH    : /api//jobseeker/{username}
     * METHOD  : GET
     * INPUT   : @path variable id
     * RESPONSE: job seeker
     */
    @GetMapping("/username/{username}")
    public JobSeekerDTO getJobSeekerByUsername(@PathVariable String username) {
        logger.info("Fetching Job seeker with username: " + username);
        return jobSeekerService.getJobSeekerByUsername(username);
    }

// ------------------------- Update Job Seeker profile (JS)---------------------------------------------
    /*called in component/jobseeker/UpdateProfile
     * AIM     : To update Job Seeker details
     * PATH    : /api/jobseeker/update/me
     * METHOD  : PUT
     * INPUT   : jobSeekerId (path variable), JobSeeker (request body)
     * RESPONSE: String 
     *here we rae not using jobSeekerId  as a path variable, we are exyracting the logged in user name from principlal obj.
     *which gives the logged in user name from JWT token
     *then we are calling JobSeekerDTO , which includedds the jobSeekerId.
     *and this id is passed in service method...so js is dynamically determined bassed on logged in user 
     */
    @PutMapping("/update/me")
    public ResponseEntity<String> updateMyProfile(@RequestBody JobSeeker jobSeeker, Principal principal) {
        //logger
    	logger.info("Updating profile of logged-in Job Seeker...");
    	
    	//get logged in user name
        JobSeekerDTO existing = jobSeekerService.getJobSeekerByUsername(principal.getName());
        //then we call service to update profile using the current id
        jobSeekerService.updateJobSeeker(existing.getJobSeekerId(), jobSeeker);
        
        return ResponseEntity.ok("Profile updated successfully.");
    }

    
      
    
    
  //--------------------------------------------------------------------------------------------------- 
   //-----------------------------------------FOE EX (to be implmented yet in FE)---------------------------------------------------------- 

    // ------------------------- Get Job Seeker by ID(EX) ---------------------------------------------
    /*
     * AIM     : To get Job Seeker details by ID
     * PATH    : /api/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId (path variable)
     * RESPONSE: JobSeeker
     */
    @GetMapping("/{jobSeekerId}")
    
    public JobSeekerDTO getJobSeekerById(@PathVariable int jobSeekerId) {
    	//logger
    	logger.info("Fetching Job Seeker with ID: ");
        return  jobSeekerService.getJobSeekerById(jobSeekerId);
    }


    // ------------------------- Delete Job Seeker(EX) ---------------------------------------------
    /*
     * AIM     : To delete a Job Seeker by ID (admin)
     * PATH    : /api/jobseekers/{jobSeekerId}
     * METHOD  : DELETE
     * INPUT   : jobSeekerId (path variable)
     * RESPONSE: String 
     */
    @DeleteMapping("/delete/{jobSeekerId}")
    public ResponseEntity<String> deleteJobSeeker(@PathVariable int jobSeekerId) {
       //logger
    	logger.info("Deleting profile of logged-in Job Seeker...");
    	
    	jobSeekerService.deleteJobSeeker(jobSeekerId);
        return ResponseEntity.status(HttpStatus.OK).body("Job Seeker deleted");
    }


    // ------------------------- Get All Job Seekers (EX) ---------------------------------------------
    /*
     * AIM     : To get a list of all registered Job Seekers
     * PATH    : /api/jobseekers
     * METHOD  : GET
     * RESPONSE: List<JobSeeker> (all Job Seeker records)
     */
    @GetMapping("/all")
    public List<JobSeekerDTO> getAllJobSeekers() {
    	//logger
        logger.info("Fetching all Job Seekers...");
        return jobSeekerService.getAllJobSeekers();
    }
    
}
