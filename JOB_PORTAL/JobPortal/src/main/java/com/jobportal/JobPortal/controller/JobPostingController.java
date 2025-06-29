package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.dto.JobPostingDTO;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.repository.HrRepository;
import com.jobportal.JobPortal.service.JobPostingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


/*implemented logger, dto, batch insert for job posting, paging for get all jobs*/
//implementd principle interface

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/jobs")

public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;
    @Autowired
    private HrRepository hrRepository;
    

  //implementing logger
    private Logger logger = LoggerFactory.getLogger("JobPostingController");
    

    //use the Principal interface instead of passing the hrId as a path variable
    
 // ----------------- Post a new Job (HR)----------------------
    /* FE: HR ------> PostJobs
     * thi allows a logged in hr to post the job
     * using the prinvipal obj. to fect the username of the logged in hr
     * AIM     : Allows an HR to post a new job
     * PATH    : /api/job/add
     * METHOD  : POST
     * INPUT   : JobPosting object in request body,principal interface
     * RESPONSE: Returns the saved JobPosting with generated ID and details
     */    
	@PostMapping("/add") 
	public JobPosting postJobs(Principal principal, @RequestBody JobPosting jobPosting) {
		String username = principal.getName(); // LoggedIn hr
		//logger
    	logger.info("Registering Job Seeker...");
		return jobPostingService.postJobs(jobPosting, username);
	}
    
    
//implemented paging in react
// ----------------- Get All Jobs -------------------------
    /* FE : JOBSEEKER----> JobsList
     * this returns all the available job postimgs
     * AIM     : Retrieve all job postings
     * PATH    : /api/jobs
     * METHOD  : GET
     * RESPONSE: List of all JobPosting objects
     */
	@GetMapping("/all")
	public List<JobPostingDTO> getAllJobs(){
		//logger
    	logger.info("getting all jobs...");
		return jobPostingService.getAllJobs();
	}

	
    // ----------------- Get Job by ID ------------------------
    /* FE : HR----> EditJobs
     * AIM     : Retrieve a job posting by its ID
     * PATH    : /api/jobs/{id}
     * METHOD  : GET
     * INPUT   : Job ID as path variable
     * RESPONSE: JobPosting object matching the ID
     */
    @GetMapping("/jobId/{jobId}")
    public ResponseEntity<JobPostingDTO> getJobById(@PathVariable int jobId) {
      
    	JobPostingDTO dto = jobPostingService.getJobById(jobId);
        logger.info("DTO fetched: " + dto);
        return ResponseEntity.ok(dto);
    }
    
// ------------------------ Update Job by ID ------------------------
    /* FE : HR----> EditJobs
     * this allows to update the job, the updated job is passed in request body
     * AIM     : Update an existing job posting's details
     * PATH    : /api/jobs/{id}
     * METHOD  : PUT
     * INPUT   : Job ID as path variable, updated JobPosting object in request body
     * RESPONSE: Updated JobPosting object
     */
	@PutMapping("/update/{jobId}")
	public ResponseEntity<JobPosting> updateJobPosting(@PathVariable int jobId, 
	                                                @RequestBody JobPosting updatedJob, 
	                                                Principal principal) {
	    logger.info("Updating job posting by logged-in HR...");
	    String username = principal.getName(); //logged-in HR
	    //fetched hr obj.
	    Hr hr = hrRepository.getHrByUsername(username);
	    JobPosting updated = jobPostingService.updateJob(jobId, updatedJob,hr); //update job
	    //logger
	    logger.info("Updating job with id: " + jobId);
	    //return updated job
	    return ResponseEntity.ok(updated);
	}
	
	
// --------------- Delete Job by ID ------------------------
    /* FE : HR----> DeleteJob
     * AIM     : Delete a job posting by its ID
     * PATH    : /api/jobs/{id}
     * METHOD  : DELETE
     * INPUT   : Job ID as path variable
     * RESPONSE: Confirmation message on successful deletion
     */
	@DeleteMapping("/delete/{jobId}")
	public ResponseEntity<?> deleteJob(@PathVariable int jobId, Principal principal) {
	    //delete job
	    jobPostingService.deleteJob(jobId); 
	    return ResponseEntity.ok("Job with ID " + jobId + " has been deleted successfully.");
	}
	
// -------------- Search Jobs -------------------
    /* FE : JS----> Jshome
     * this allows to serach jons using optional parameters as title, location. company
     * AIM     : Search jobs by title, location, and/or company
     * PATH    : /api/jobs/search
     * METHOD  : GET
     * INPUT   : Optional query parameters - job_title, location, company
     * RESPONSE: List of JobPosting objects matching search criteria
     */
   @GetMapping("/search")
   public List<JobPostingDTO> searchJobs(
           @RequestParam(required = false) String job_title,
           @RequestParam(required = false) String location,
           @RequestParam(required = false) String company
          ){
        //logger
       logger.info("Searching jobs...");
       
       return jobPostingService.searchJobs(job_title, location, company); //return the filtered list after serach
   }
   
   
   // ------------------------- get jobs by-hr ---------------------------------------------

   //FE : JS----> Jobs
	@GetMapping("/by-hr")
	public List<JobPosting> getJobsByHr(Principal principal){
		String username = principal.getName();
		List<JobPosting> courses = jobPostingService.getJobsByHr(username);
		logger.info("fethcing job");
		return courses;
	}

}
