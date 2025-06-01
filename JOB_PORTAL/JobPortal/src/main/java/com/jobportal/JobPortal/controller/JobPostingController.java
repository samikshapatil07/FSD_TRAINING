package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.service.JobPostingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPostingController {

    @Autowired
    private JobPostingService jobPostingService;

    // ----------------- Post a new Job (HR)----------------------
    /*
     * AIM     : Allows an HR to post a new job
     * PATH    : /api/jobs/{hrId}
     * METHOD  : POST
     * INPUT   : JobPosting object in request body, HR id as path variable
     * RESPONSE: Returns the saved JobPosting with generated ID and details
     */
    @PostMapping("/{hrId}")
    public ResponseEntity<JobPosting> postJob(@RequestBody JobPosting jobPosting,
                                              @PathVariable Long hrId) {
        JobPosting saved = jobPostingService.postJob(jobPosting, hrId);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ----------------- Get All Jobs -------------------------
    /*
     * AIM     : Retrieve all job postings
     * PATH    : /api/jobs
     * METHOD  : GET
     * RESPONSE: List of all JobPosting objects
     */
    @GetMapping
    public ResponseEntity<?> getAllJobs() {
        List<JobPosting> jobs = jobPostingService.getAllJobs();
        return ResponseEntity.ok(jobs);
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
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        JobPosting job = jobPostingService.getJobById(id);
        return ResponseEntity.ok(job);
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
    public ResponseEntity<List<JobPosting>> searchJobs(
            @RequestParam(required = false) String job_title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String company) {
        List<JobPosting> jobs = jobPostingService.searchJobs(job_title, location, company);
        return ResponseEntity.ok(jobs);
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
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody JobPosting updatedJob) {
        JobPosting job = jobPostingService.updateJob(id, updatedJob);
        return ResponseEntity.ok(job);
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
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobPostingService.deleteJob(id);
        return ResponseEntity.ok("Job with ID " + id + " has been deleted successfully.");
    }
}
