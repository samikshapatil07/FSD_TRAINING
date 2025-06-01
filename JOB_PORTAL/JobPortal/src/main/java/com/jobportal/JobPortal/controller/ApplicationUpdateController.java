package com.jobportal.JobPortal.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobportal.JobPortal.model.ApplicationUpdate;
import com.jobportal.JobPortal.service.ApplicationUpdateService;

@RestController
@RequestMapping("/api/updates")
public class ApplicationUpdateController {

    @Autowired
    private ApplicationUpdateService updateService;

    // ------------------- Get all updates by Application ID -----------------
    /*
     * AIM     : To retrieve all updates related to a specific application
     * PATH    : /api/updates/application/{appId}
     * METHOD  : GET
     * INPUT   : appId (application ID path variable)
     * RESPONSE: List<ApplicationUpdate> (list of updates)
     */
    @GetMapping("/application/{appId}")
    public ResponseEntity<List<ApplicationUpdate>> getByApplicationId(@PathVariable Integer appId) {
        List<ApplicationUpdate> updates = updateService.getUpdatesByApplicationId(appId);
        return ResponseEntity.ok(updates);
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
    public ResponseEntity<List<ApplicationUpdate>> getByJobSeekerId(@PathVariable Integer jobSeekerId) {
        List<ApplicationUpdate> updates = updateService.getUpdatesByJobSeekerId(jobSeekerId);
        return ResponseEntity.ok(updates);
    }
}
