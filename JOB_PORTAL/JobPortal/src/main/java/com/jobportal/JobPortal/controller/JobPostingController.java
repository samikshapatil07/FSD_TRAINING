package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.dto.JobPostingDTO;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.service.JobPostingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;

  //implementing logger
    private Logger logger = LoggerFactory.getLogger("JobPostingController");
    
    //implementing dto (convert JobPosting to JobPostingDTO)
    private JobPostingDTO convertToDTO(JobPosting job) {
        return new JobPostingDTO(
            job.getJobId(),
            job.getJobTitle(),
            job.getDescription(),
            job.getSkills(),
            job.getLocation(),
            job.getSalary(),
            job.getDepartment(),
            job.getCompany(),
            job.getExperience(),
            job.getCreatedAt()
        );
    }
    
    private List<JobPostingDTO> convertListToDTO(List<JobPosting> jobs) {
        List<JobPostingDTO> dtoList = new ArrayList<>();
        for (JobPosting job : jobs) {
            dtoList.add(convertToDTO(job));
        }
        return dtoList;
    }
    // ----------------- Post a new Job (HR)----------------------
    /*
     * AIM     : Allows an HR to post a new job
     * PATH    : /api/jobs/{hrId}
     * METHOD  : POST
     * INPUT   : JobPosting object in request body, HR id as path variable
     * RESPONSE: Returns the saved JobPosting with generated ID and details
     */
    @PostMapping("/{hrId}")
    public ResponseEntity<JobPostingDTO> postJob(@RequestBody JobPosting jobPosting,
                                              @PathVariable int hrId) {
        JobPosting saved = jobPostingService.postJob(jobPosting, hrId);
        //dto
        JobPostingDTO dto = convertToDTO(saved);
        //logger
       logger.info("Posting a new job by " + hrId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // ----------------- Get All Jobs -------------------------
    /*
     * AIM     : Retrieve all job postings
     * PATH    : /api/jobs
     * METHOD  : GET
     * RESPONSE: List of all JobPosting objects
     */
    @GetMapping("/api/jobs?page=0&size=5")
    public ResponseEntity<List<JobPostingDTO>> getAllJobs(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "5") int size){ //implementing paging
        List<JobPosting> jobs = jobPostingService.getAllJobs(page, size).getContent();
        //logger
        logger.info("Retrieving all jobs...");
        //dto
        List<JobPostingDTO> dtoList = convertListToDTO(jobs);
        return ResponseEntity.ok(dtoList);
    }

    // ----------------- Get Job by ID ------------------------
    /*
     * AIM     : Retrieve a job posting by its ID
     * PATH    : /api/jobs/{id}
     * METHOD  : GET
     * INPUT   : Job ID as path variable
     * RESPONSE: JobPosting object matching the ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<JobPostingDTO> getJobById(@PathVariable int id) {
        JobPosting job = jobPostingService.getJobById(id);
        //logger
        logger.info("Getting job with id: " + id);
        //dto
        JobPostingDTO dto = convertToDTO(job);
        return ResponseEntity.ok(dto);
    }

    // -------------- Search Jobs by Criteria -------------------
    /*
     * AIM     : Search jobs by title, location, and/or company
     * PATH    : /api/jobs/search
     * METHOD  : GET
     * INPUT   : Optional query parameters - job_title, location, company
     * RESPONSE: List of JobPosting objects matching search criteria
     */
    @GetMapping("/search")
    public ResponseEntity<List<JobPostingDTO>> searchJobs(
            @RequestParam(required = false) String job_title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String company) {
        List<JobPosting> jobs = jobPostingService.searchJobs(job_title, location, company);
        //logger
        logger.info("Searching jobs...");
        //dto
        List<JobPostingDTO> dtoList = convertListToDTO(jobs);
        return ResponseEntity.ok(dtoList);
    }
    // --------------- Update Job by ID ------------------------
    /*
     * AIM     : Update an existing job posting's details
     * PATH    : /api/jobs/{id}
     * METHOD  : PUT
     * INPUT   : Job ID as path variable, updated JobPosting object in request body
     * RESPONSE: Updated JobPosting object
     */
    @PutMapping("/{id}")
    public ResponseEntity<JobPostingDTO> updateJob(@PathVariable int id, @RequestBody JobPosting updatedJob) {
        JobPosting job = jobPostingService.updateJob(id, updatedJob);
        //logger
        logger.info("Updating job with id: " + id);
        //dto
        JobPostingDTO dto = convertToDTO(job);
        return ResponseEntity.ok(dto);
    }

    // --------------- Delete Job by ID ------------------------
    /*
     * AIM     : Delete a job posting by its ID
     * PATH    : /api/jobs/{id}
     * METHOD  : DELETE
     * INPUT   : Job ID as path variable
     * RESPONSE: Confirmation message on successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable int id) {
        jobPostingService.deleteJob(id);
        //logger
        logger.info("Deleting job with id:"+ id);
        return ResponseEntity.ok("Job with ID " + id + " has been deleted successfully.");
    }
}
