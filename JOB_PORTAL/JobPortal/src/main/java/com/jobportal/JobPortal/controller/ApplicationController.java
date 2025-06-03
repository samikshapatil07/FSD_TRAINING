package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.service.ApplicationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;
    
    //implementing logger
    private Logger logger = LoggerFactory.getLogger("ApplicationController");

    // ------------------------- Apply to Job (Job Seeker) ---------------------------------------------
    /*
     * AIM     : Allows a job seeker to apply for a job
     * PATH    : /api/applications
     * METHOD  : POST
     * INPUT   : Application object from request body
     * RESPONSE: Saved Application object
     */
    @PostMapping("/{seekerId}/{jobId}")
    public Application createApplication(@PathVariable int seekerId,
                                         @PathVariable int jobId,
                                         @RequestBody Application application) {
        logger.info("Seeker ID given is : " + seekerId);
        return applicationService.saveApplication(seekerId, jobId,application);
    }
   
    // ------------------------- Get Application by ID ---------------------------------------------
    /*
     * AIM     : Retrieve a specific application by its ID
     * PATH    : /api/applications/{id}
     * METHOD  : GET
     * INPUT   : applicationId (path variable)
     * RESPONSE: Application object or null
     */
    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        logger.info("Seeker ID given is : " + id);
        return applicationService.getApplicationById(id).orElse(null);
    }

    // ------------------------- Delete Application (Job Seeker) ---------------------------------------------
    /*
     * AIM     : Withdraw an application
     * PATH    : /api/applications/{id}
     * METHOD  : DELETE
     * INPUT   : applicationId (path variable)
     * RESPONSE: void
     */
    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        logger.info("Deleted Application with ID : " + id);
        applicationService.deleteApplication(id);
    }

    // ------------------------- Get Applications by Job ID (HR) ---------------------------------------------
    /*
     * AIM     : Get all applications for a specific job
     * PATH    : /api/applications/job/{jobId}
     * METHOD  : GET
     * INPUT   : jobId (path variable)
     * RESPONSE: List of Application objects
     */
    @GetMapping("/job/{jobId}")
    public List<Application> getByJobId(@PathVariable Long jobId) {
        logger.info("Application with Job ID : " + jobId);
        return applicationService.getApplicationsByJobId(jobId);
    }

    // ------------------------- Get All Applications (Admin/HR) ---------------------------------------------
    /*
     * AIM     : Retrieve all applications
     * PATH    : /api/applications
     * METHOD  : GET
     * RESPONSE: List of all Application objects
     */
    @GetMapping
    public List<Application> getAllApplications() {
        logger.info("Fetched all applications.. ");
        return applicationService.getAllApplications();
    }

    // ------------------------- Get Applications by Job Seeker ID ---------------------------------------------
    /*
     * AIM     : View all applications submitted by a job seeker
     * PATH    : /api/applications/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * INPUT   : jobSeekerId (path variable)
     * RESPONSE: List of Application objects
     */
    @GetMapping("/jobseeker/{jobSeekerId}")
    public List<Application> getByJobSeekerId(@PathVariable Long jobSeekerId) {
        logger.info("Application with Job Seeker ID : " + jobSeekerId);
        return applicationService.getApplicationsByJobSeekerId(jobSeekerId);
    }

    // ------------------------- Track Application Status ---------------------------------------------
    /*
     * AIM     : Track status of a specific application
     * PATH    : /api/applications/status/{applicationId}
     * METHOD  : GET
     * INPUT   : applicationId (path variable)
     * RESPONSE: Application status as String or "Application not found"
     */
    @GetMapping("/status/{applicationId}")
    public String trackStatus(@PathVariable Long applicationId) {
        logger.info("Application Status with app. ID : " + applicationId);
        return applicationService.getApplicationById(applicationId)
                .map(app -> app.getStatus().toString())
                .orElse("Application not found");
    }

    // ------------------------- Update Application Details (Job Seeker) ---------------------------------------------
    /*
     * AIM     : Update resume or other application details
     * PATH    : /api/applications/{id}
     * METHOD  : PUT
     * INPUT   : applicationId (path variable), updated Application (request body)
     * RESPONSE: Updated Application object
     */
    @PutMapping("/{id}")
    public Application updateApplication(@PathVariable Long id, @RequestBody Application updatedApp) {
        logger.info("ID given to update is : " + id);
        return applicationService.updateApplication(id, updatedApp);
    }

    // -------------------------  Reject Application (HR) ---------------------------------------------
    /*
     * AIM     : Update status to SHORTLISTED or REJECTED
     * PATH    : /api/applications/{id}/status?status=SHORTLISTED
     * METHOD  : PATCH
     * INPUT   : applicationId (path variable), status (query param)
     * RESPONSE: Updated Application object
     */
    @PatchMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @RequestParam Application.Status status) {
        logger.info("Application Status for " + id);
        return applicationService.updateApplicationStatus(id, status);
    }
}
