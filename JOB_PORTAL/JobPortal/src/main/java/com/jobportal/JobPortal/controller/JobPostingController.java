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

    // ----------- Post a new Job -----------
    @PostMapping("/{hrId}")
    public ResponseEntity<JobPosting> postJob(@RequestBody JobPosting jobPosting,
                                              @PathVariable Long hrId) {
        JobPosting saved = jobPostingService.postJob(jobPosting, hrId);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    // ----------- Get All Jobs -----------
    @GetMapping
    public ResponseEntity<?> getAllJobs() {
        List<JobPosting> jobs = jobPostingService.getAllJobs();
        return ResponseEntity.ok(jobs);
    }

    // ----------- Get Job by ID -----------
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(@PathVariable Long id) {
        JobPosting job = jobPostingService.getJobById(id);
        return ResponseEntity.ok(job);
    }

    // ----------- Search Jobs by title, location, company -----------
    @GetMapping("/search")
    public ResponseEntity<List<JobPosting>> searchJobs(
            @RequestParam(required = false) String job_title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) String company) {
        List<JobPosting> jobs = jobPostingService.searchJobs(job_title, location, company);
        return ResponseEntity.ok(jobs);
    }

    // ----------- Update Job by ID -----------
    @PutMapping("/{id}")
    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody JobPosting updatedJob) {
        JobPosting job = jobPostingService.updateJob(id, updatedJob);
        return ResponseEntity.ok(job);
    }

    // ----------- Delete Job by ID -----------
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobPostingService.deleteJob(id);
        return ResponseEntity.ok("Job with ID " + id + " has been deleted successfully.");
    }
}
