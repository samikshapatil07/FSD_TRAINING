//package com.jobportal.JobPortal.controller;
//
//import com.jobportal.JobPortal.model.JobPosting;
//import com.jobportal.JobPortal.service.JobPostingService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/jobs")
//public class JobPostingController {
//
//    @Autowired
//    private JobPostingService jobPostingService;
//
//    // ------------------- Post a new Job -------------------------
//    /*
//     * AIM    : To post a new job
//     * PATH   : /api/jobs
//     * METHOD : POST
//     * INPUT  : JobPosting (request body)
//     * RESPONSE: JobPosting (saved job)
//     */
//    @PostMapping("/jobs/{hrId}")
//    public ResponseEntity<JobPosting> postJob(@RequestBody JobPosting jobPosting,
//                                              @PathVariable Long hrId) {
//        JobPosting saved = jobPostingService.postJob(jobPosting, hrId);
//        return new ResponseEntity<>(saved, HttpStatus.CREATED);
//    }
//
//    // ------------------- Get All Jobs -------------------------
//    /*
//     * AIM    : To get all jobs
//     * PATH   : /api/jobs
//     * METHOD : GET
//     * RESPONSE: List<JobPosting>
//     */
//    @GetMapping
//    public ResponseEntity<?> getAllJobs() {
//        List<JobPosting> jobs = jobPostingService.getAllJobs();
//        return ResponseEntity.ok(jobs);
//    }
//
//    // ------------------- Get Job by ID -------------------------
//    /*
//     * AIM    : To get job by ID
//     * PATH   : /api/jobs/{id}
//     * METHOD : GET
//     * INPUT  : id (path variable)
//     * RESPONSE: JobPosting
//     */
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getJobById(@PathVariable Long id) {
//        JobPosting job = jobPostingService.getJobById(id);
//        return ResponseEntity.ok(job);
//    }
//
//    // ------------------- Get Jobs by Location -------------------------
//    /*
//     * AIM    : To get jobs by location
//     * PATH   : /api/jobs/location
//     * METHOD : GET
//     * INPUT  : location (query param)
//     * RESPONSE: List<JobPosting>
//     */
//    @GetMapping("/location")
//    public ResponseEntity<?> getJobsByLocation(@RequestParam String location) {
//        List<JobPosting> jobs = jobPostingService.getJobsByLocation(location);
//        return ResponseEntity.ok(jobs);
//    }
//
//    // ------------------- Get Jobs by Skills -------------------------
//    /*
//     * AIM    : To get jobs by skills
//     * PATH   : /api/jobs/skills
//     * METHOD : GET
//     * INPUT  : skills (query param)
//     * RESPONSE: List<JobPosting>
//     */
//    @GetMapping("/skills")
//    public ResponseEntity<?> getJobsBySkills(@RequestParam String skills) {
//        List<JobPosting> jobs = jobPostingService.getJobsBySkills(skills);
//        return ResponseEntity.ok(jobs);
//    }
//
//    // ------------------- Get Jobs by Company -------------------------
//    /*
//     * AIM    : To get jobs by company
//     * PATH   : /api/jobs/company
//     * METHOD : GET
//     * INPUT  : company (query param)
//     * RESPONSE: List<JobPosting>
//     */
//    @GetMapping("/company")
//    public ResponseEntity<?> getJobsByCompany(@RequestParam String company) {
//        List<JobPosting> jobs = jobPostingService.getJobsByCompany(company);
//        return ResponseEntity.ok(jobs);
//    }
//
//    
// // ------------------- Update Job by ID -------------------------
//    /*
//     * AIM    : To update an existing job posting
//     * PATH   : /api/jobs/{id}
//     * METHOD : PUT
//     * INPUT  : id (path variable), updated JobPosting (request body)
//     * RESPONSE: JobPosting (updated job)
//     */
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateJob(@PathVariable Long id, @RequestBody JobPosting updatedJob) {
//        JobPosting job = jobPostingService.updateJob(id, updatedJob);
//        return ResponseEntity.ok(job);
//    }
//    
// // ------------------- Delete Job by ID -------------------------
//    /*
//     * AIM    : To delete job by ID
//     * PATH   : /api/jobs/{id}
//     * METHOD : DELETE
//     * INPUT  : id (path variable)
//     * RESPONSE: String (confirmation message)
//     */
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
//        jobPostingService.deleteJob(id);
//        return ResponseEntity.ok("Job with ID " + id + " has been deleted successfully.");
//    }
//}
