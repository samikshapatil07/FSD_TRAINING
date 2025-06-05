package com.jobportal.JobPortal.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.JobPortal.dto.ApplicationUpdateDTO;
import com.jobportal.JobPortal.model.ApplicationUpdate;
import com.jobportal.JobPortal.service.ApplicationUpdateService;

@RestController
@RequestMapping("/api/updates")
public class ApplicationUpdateController {

    @Autowired
    private ApplicationUpdateService updateService;
    
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
    public ResponseEntity<List<ApplicationUpdateDTO>> getByApplicationId(@PathVariable Integer appId) {
        List<ApplicationUpdate> updates = updateService.getUpdatesByApplicationId(appId);
        List<ApplicationUpdateDTO> result = new ArrayList<>();

        for (ApplicationUpdate update : updates) {
            ApplicationUpdateDTO dto = new ApplicationUpdateDTO();
            dto.setUpdateId(update.getUpdateId());
            dto.setUpdatedResumePath(update.getUpdatedResumePath());
            dto.setUpdatedOn(update.getUpdatedOn().toString());
            dto.setApplicationId(update.getApplication().getApplicationId());
            dto.setJobSeekerId(update.getJobSeeker().getJobSeekerId());
            result.add(dto);
        }

        logger.info("Update for Application ID is : " + appId);
        return ResponseEntity.ok(result);
    }

    // ------------------- Get all updates by Job Seeker ID -------------------
    /*
     * AIM     : To retrieve all updates related to applications by a specific job seeker
     * PATH    : /api/updates/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId (job seeker ID path variable)
     * RESPONSE: List<ApplicationUpdate> (list of updates)
     */
    @GetMapping("/jobseeker/{jobSeekerId}")
    public ResponseEntity<List<ApplicationUpdateDTO>> getByJobSeekerId(@PathVariable Integer jobSeekerId) {
        List<ApplicationUpdate> updates = updateService.getUpdatesByJobSeekerId(jobSeekerId);
        List<ApplicationUpdateDTO> result = new ArrayList<>();

        for (ApplicationUpdate update : updates) {
            ApplicationUpdateDTO dto = new ApplicationUpdateDTO();
            dto.setUpdateId(update.getUpdateId());
            dto.setUpdatedResumePath(update.getUpdatedResumePath());
            dto.setUpdatedOn(update.getUpdatedOn().toString());
            dto.setApplicationId(update.getApplication().getApplicationId());
            dto.setJobSeekerId(update.getJobSeeker().getJobSeekerId());
            result.add(dto);
        }

        logger.info("Update for JobSeeker ID is : " + jobSeekerId);
        return ResponseEntity.ok(result);
    }
}
