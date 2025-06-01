package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.service.JobSeekerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    // ------------------------- Register Job Seeker ---------------------------------------------
    /*
     * AIM     : To register a new Job Seeker
     * PATH    : /api/jobseekers/register
     * METHOD  : POST
     * INPUT   : JobSeeker (request body)
     * RESPONSE: JobSeeker (saved Job Seeker object)
     */
    @PostMapping("/register")
    public ResponseEntity<JobSeeker> registerJobSeeker(@RequestBody JobSeeker jobSeeker) {
        JobSeeker savedJobSeeker = jobSeekerService.registerjobSeeker(jobSeeker);
        return ResponseEntity.ok(savedJobSeeker);
    }

    // ------------------------- Get Job Seeker by ID ---------------------------------------------
    /*
     * AIM     : To get Job Seeker details by ID
     * PATH    : /api/jobseekers/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId (path variable)
     * RESPONSE: JobSeeker (Job Seeker object)
     */
    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<?> getJobSeekerById(@PathVariable Long jobSeekerId) {
        JobSeeker js = jobSeekerService.getJobSeekerById(jobSeekerId);
        return ResponseEntity.ok(js);
    }

    // ------------------------- Update Job Seeker ---------------------------------------------
    /*
     * AIM     : To update Job Seeker details
     * PATH    : /api/jobseekers/{jobSeekerId}
     * METHOD  : PUT
     * INPUT   : jobSeekerId (path variable), JobSeeker (request body)
     * RESPONSE: String (confirmation message)
     */
    @PutMapping("/{jobSeekerId}")
    public ResponseEntity<?> updateJobSeeker(@PathVariable Long jobSeekerId, @RequestBody JobSeeker jobSeeker) {
        JobSeeker updated = jobSeekerService.updateJobSeeker(jobSeekerId, jobSeeker);
        return ResponseEntity.ok("Job Seeker updated successfully");
    }

    // ------------------------- Delete Job Seeker ---------------------------------------------
    /*
     * AIM     : To delete a Job Seeker by ID
     * PATH    : /api/jobseekers/{jobSeekerId}
     * METHOD  : DELETE
     * INPUT   : jobSeekerId (path variable)
     * RESPONSE: String (confirmation message)
     */
    @DeleteMapping("/{jobSeekerId}")
    public ResponseEntity<?> deleteJobSeeker(@PathVariable Long jobSeekerId) {
        jobSeekerService.deleteJobSeeker(jobSeekerId);
        return ResponseEntity.ok("Job Seeker deleted successfully");
    }

    // ------------------------- Get All Job Seekers ---------------------------------------------
    /*
     * AIM     : To get a list of all registered Job Seekers
     * PATH    : /api/jobseekers
     * METHOD  : GET
     * RESPONSE: List<JobSeeker> (all Job Seeker records)
     */
    @GetMapping
    public ResponseEntity<?> getAllJobSeekers() {
        List<JobSeeker> list = jobSeekerService.getAllJobSeekers();
        return ResponseEntity.ok(list);
    }
}
