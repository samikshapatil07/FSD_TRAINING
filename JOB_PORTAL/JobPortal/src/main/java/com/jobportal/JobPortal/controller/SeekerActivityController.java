package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.dto.SeekerActivityDTO;
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

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/seeker-activities")
public class SeekerActivityController {

    @Autowired
    private SeekerActivityService service;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("SeekerActivityController");
    
  //implementing dto once here and then using it in methods by convertToDTO
    private List<SeekerActivityDTO> convertToDTOList(List<SeekerActivity> entities) {
        List<SeekerActivityDTO> dtos = new ArrayList<>();
        for (SeekerActivity entity : entities) {
            SeekerActivityDTO dto = new SeekerActivityDTO();
            dto.setActivityId(entity.getActivityId());
            dto.setActivityType(entity.getActivityType());
            dto.setDescription(entity.getDescription());
            dto.setTimestamp(entity.getTimestamp());
            if (entity.getJobSeeker() != null) {
                dto.setJobSeekerId(entity.getJobSeeker().getJobSeekerId());
            }
            dtos.add(dto);
        }
        return dtos;
    }

    // ----------------- get all seeker activity ----------------------
    /**
     * AIM     : Retrieve all seeker activities
     * PATH    : /api/seeker-activities
     * METHOD  : GET
     * RESPONSE: List of all SeekerActivity objects
     */
    @GetMapping
    public ResponseEntity<List<SeekerActivityDTO>> getAllActivities() {
        List<SeekerActivity> activities = service.getAllActivities();
        //dto
        List<SeekerActivityDTO> dtoList = convertToDTOList(activities);
        //logger
        logger.info("Getting all job seekers activity....");
        return ResponseEntity.ok(dtoList);
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
    public ResponseEntity<List<SeekerActivityDTO>> getByJobSeeker(@PathVariable int jobSeekerId) {
        //logger
    	logger.info("Getting job seekers activity for id: " + jobSeekerId);
        List<SeekerActivity> activities = service.getActivitiesByJobSeekerId(jobSeekerId);
        //dto
        List<SeekerActivityDTO> dtoList = convertToDTOList(activities);
        return ResponseEntity.ok(dtoList);
    }
    }
