package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.dto.JobSeekerDTO;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.service.JobSeekerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

//implemented principle interface for js 
@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("JobSeekerController");
    
   //implementing dto by convertToDTO
    private JobSeekerDTO convertToDTO(JobSeeker js) {
        JobSeekerDTO dto = new JobSeekerDTO();
        dto.setJobSeekerId(js.getJobSeekerId());
        dto.setName(js.getName());
        dto.setEducation(js.getEducation());
        dto.setSkills(js.getSkills());
        dto.setExperience(js.getExperience());
        if (js.getUser() != null) {
            dto.setUserId(js.getUser().getId());
        }
        return dto;
    }
      //list for get all js
    private List<JobSeekerDTO> convertToDTOList(List<JobSeeker> seekers) {
        List<JobSeekerDTO> dtoList = new ArrayList<>();
        for (JobSeeker js : seekers) {
            dtoList.add(convertToDTO(js));
        }
        return dtoList;
    }

    // ------------------------- Register Job Seeker ---------------------------------------------
    /*
     * AIM     : To register a new Job Seeker
     * PATH    : /api/jobseekers/register
     * METHOD  : POST
     * INPUT   : JobSeeker (request body)
     * RESPONSE: JobSeeker (saved Job Seeker object)
     */
    @PostMapping("/register")
    public ResponseEntity<JobSeekerDTO> registerJobSeeker(@RequestBody JobSeeker jobSeeker) {
        //logger
    	logger.info("Registering Job Seeker...");
        JobSeeker savedJobSeeker = jobSeekerService.registerjobSeeker(jobSeeker);
        //dto
        JobSeekerDTO dto = convertToDTO(savedJobSeeker);
        return ResponseEntity.ok(dto);
    }

    
    // ------------------- Get Logged-in Job Seeker (Self) -------------------
    @GetMapping("/me")
    public ResponseEntity<JobSeekerDTO> getMyProfile(Principal principal) {
        logger.info("Fetching logged-in Job Seeker details...");
        JobSeeker js = jobSeekerService.getJobSeekerByUsername(principal.getName());
        return ResponseEntity.ok(convertToDTO(js));
    }

    
    // ------------------------- Get Job Seeker by ID(admin) ---------------------------------------------
    /*
     * AIM     : To get Job Seeker details by ID
     * PATH    : /api/jobseekers/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId (path variable)
     * RESPONSE: JobSeeker (Job Seeker object)
     */
    @GetMapping("/{jobSeekerId}")
    public ResponseEntity<JobSeekerDTO> getJobSeekerById(Principal principal) {
        //logger
    	logger.info("Fetching Job Seeker with ID: ");
        JobSeeker js = jobSeekerService.getJobSeekerByUsername(principal.getName());
        //dto
        JobSeekerDTO dto = convertToDTO(js);
        return ResponseEntity.ok(dto);
    }

    // ------------------------- Update Job Seeker ---------------------------------------------
    /*
     * AIM     : To update Job Seeker details
     * PATH    : /api/jobseekers/{jobSeekerId}
     * METHOD  : PUT
     * INPUT   : jobSeekerId (path variable), JobSeeker (request body)
     * RESPONSE: String (confirmation message)
     */
    @PutMapping("/me")
    public ResponseEntity<String> updateMyProfile(@RequestBody JobSeeker jobSeeker, Principal principal) {
        //logger
    	logger.info("Updating profile of logged-in Job Seeker...");
        JobSeeker existing = jobSeekerService.getJobSeekerByUsername(principal.getName());
        jobSeekerService.updateJobSeeker(existing.getJobSeekerId(), jobSeeker);
        return ResponseEntity.ok("Profile updated successfully.");
    }


    // ------------------------- Delete Job Seeker ---------------------------------------------
    /*
     * AIM     : To delete a Job Seeker by ID (admin)
     * PATH    : /api/jobseekers/{jobSeekerId}
     * METHOD  : DELETE
     * INPUT   : jobSeekerId (path variable)
     * RESPONSE: String (confirmation message)
     */
    @DeleteMapping("/{jobSeekerId}")
    public ResponseEntity<String> deleteMyProfile(Principal principal) {
       //logger
    	logger.info("Deleting profile of logged-in Job Seeker...");
        JobSeeker existing = jobSeekerService.getJobSeekerByUsername(principal.getName());
        jobSeekerService.deleteJobSeeker(existing.getJobSeekerId());
        return ResponseEntity.ok("Account deleted successfully.");
    }


    // ------------------------- Get All Job Seekers ---------------------------------------------
    /*
     * AIM     : To get a list of all registered Job Seekers
     * PATH    : /api/jobseekers
     * METHOD  : GET
     * RESPONSE: List<JobSeeker> (all Job Seeker records)
     */
    @GetMapping
    public ResponseEntity<List<JobSeekerDTO>> getAllJobSeekers() {
    	//logger
        logger.info("Fetching all Job Seekers...");
        List<JobSeeker> seekers = jobSeekerService.getAllJobSeekers();
        //dto
        List<JobSeekerDTO> dtoList = convertToDTOList(seekers);
        return ResponseEntity.ok(dtoList);
    }
}
