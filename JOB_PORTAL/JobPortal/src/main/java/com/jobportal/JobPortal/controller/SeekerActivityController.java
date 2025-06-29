package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.dto.SeekerActivityDTO;
import com.jobportal.JobPortal.model.SeekerActivity;
import com.jobportal.JobPortal.service.SeekerActivityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")

@RequestMapping("/api/seeker-activities")
public class SeekerActivityController {

    @Autowired
    private SeekerActivityService seekerActivityService;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("SeekerActivityController");
    
    //===================== this was for EXecutive ====to be implemented=============
    
// ----------------- get all seeker activity ----------------------
    /**
     * AIM     : Retrieve all seeker activities
     * PATH    : /api/seeker-activities
     * METHOD  : GET
     * RESPONSE: List of all SeekerActivity objects
     */
    @GetMapping
    public List<SeekerActivity> getAllActivities() {
        //logger
        logger.info("Getting all job seekers activity....");
        return seekerActivityService.getAllActivities();
    }
    // ----------------- get all seeker activity by jobSeekerId ----------------------
    /**
     * AIM     : Retrieve seeker activities by Job Seeker ID
     * PATH    : /api/seeker-activities/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId as path variable
     * RESPONSE: List of SeekerActivity objects for the specified job seeker
     */
    @GetMapping("/jobseeker/{jobSeekerId}")
    public List<SeekerActivityDTO> getActivitiesByJobSeekerId(@PathVariable int jobSeekerId) {
        return seekerActivityService.getActivitiesByJobSeekerId(jobSeekerId);
    }
}
