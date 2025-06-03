package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.model.SeekerActivity;
import com.jobportal.JobPortal.service.SeekerActivityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/seeker-activities")
public class SeekerActivityController {

    @Autowired
    private SeekerActivityService service;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("SeekerActivityController");

    // ----------------- get all seeker activity ----------------------
    /**
     * AIM     : Retrieve all seeker activities
     * PATH    : /api/seeker-activities
     * METHOD  : GET
     * RESPONSE: List of all SeekerActivity objects
     */
    @GetMapping
    public ResponseEntity<List<SeekerActivity>> getAllActivities() {
        List<SeekerActivity> activities = service.getAllActivities();
        logger.info("Getting all job seekers activity....");
        return ResponseEntity.ok(activities);
    }
    // ----------------- get all seeker activity by job id ----------------------
    /**
     * AIM     : Retrieve seeker activities by Job Seeker ID
     * PATH    : /api/seeker-activities/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId as path variable
     * RESPONSE: List of SeekerActivity objects for the specified job seeker
     */
    @GetMapping("/jobseeker/{jobSeekerId}")
    public ResponseEntity<List<SeekerActivity>> getByJobSeeker(@PathVariable Long jobSeekerId) {
        List<SeekerActivity> activities = service.getActivitiesByJobSeekerId(jobSeekerId);
        logger.info("Getting  job seekers activity for id:" + jobSeekerId);
        return ResponseEntity.ok(activities);
    }
}
