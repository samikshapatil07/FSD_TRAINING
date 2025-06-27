package com.jobportal.JobPortal.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.JobPortal.dto.ApplicationUpdateDTO;
import com.jobportal.JobPortal.repository.JobSeekerRepository;
import com.jobportal.JobPortal.service.ApplicationUpdateService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") //allows 5173 port
@RequestMapping("/api/updates")
public class ApplicationUpdateController {

    @Autowired
    private ApplicationUpdateService updateService;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
        
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("ApplicationUpdateController");

  // ------------------- Get all updates by Application ID -----------------
    /*
     * AIM     : To retrieve all updates related to a specific application
     * PATH    : /api/updates/application/{appId}
     * METHOD  : GET
     * INPUT   : appId (application ID path variable)
     * RESPONSE: List<ApplicationUpdate> (list of updates)
     */
    @GetMapping("/application/{appId}")
    public List<ApplicationUpdateDTO> getUpdatesByApplicationId(@PathVariable int appId) {
        logger.info("Fetching updates for Application ID: "+ appId);
        
        return updateService.getUpdatesByApplicationId(appId);
    }

    
    
    
    
    
    
    
    
 //=================================== for executive ==============================================================================
 // ------------------- Get all updates by Job Seeker ID -------------------
    /*
     * AIM     : To retrieve all updates related to applications by a specific job seeker
     * PATH    : /api/updates/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId (job seeker ID path variable)
     * RESPONSE: List<ApplicationUpdate> (list of updates)
     */
    @GetMapping("/app-updates/jobseeker")
    public ResponseEntity<List<ApplicationUpdateDTO>> getUpdatesByLoggedInJobSeeker(Principal principal) {
        logger.info("Fetching updates for logged-in JobSeeker");

        String username = principal.getName();  // Extract username from JWT
        int jobSeekerId = jobSeekerRepository.getJobSeekerByUsername(username).getJobSeekerId();

        List<ApplicationUpdateDTO> result = updateService.getUpdatesByJobSeekerId(jobSeekerId);
        return ResponseEntity.ok(result);
    }
}
