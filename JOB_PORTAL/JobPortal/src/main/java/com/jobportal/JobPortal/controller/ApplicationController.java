package com.jobportal.JobPortal.controller;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jobportal.JobPortal.dto.ApplicationDTO;
import com.jobportal.JobPortal.dto.ApplicationStatusDTO;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Application.Status;
import com.jobportal.JobPortal.service.ApplicationService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // aallow FE access from this origin
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    
    //implementing logger
    private Logger logger = LoggerFactory.getLogger("ApplicationController");

 // ------------------------- Apply to Job (Job Seeker) ---------------------------------------------
    /* FE: JOBSEEKER----> ApplyJob
     * AIM     : Allows a job seeker to apply for a job
     * PATH    : /api/applications/{jobId}
     * METHOD  : POST
     * INPUT   : Application object from request body
     * RESPONSE: Saved Application object
     */
    @PostMapping("apply/upload/resume/{jobId}")
    public String  applyJob(
            @PathVariable int jobId,
            @RequestParam("resume") MultipartFile file,
            Principal principal) throws IOException {
        
        String username = principal.getName(); //logged in user
        applicationService.applyJob(file, username, jobId);
        
        logger.info(" apply for job by logged in Job Seeker");
        return file.getOriginalFilename() + file.getSize(); //return the file name 
    }
    
    // ------------------------- Update Application (upload resume) JS ---------------------------------------------
    /*/* FE: JOBSEEKER----> UpdateApplication
     * AIM     : Update resume or other application details
     * PATH    : /api/applications/{id}
     * METHOD  : PUT
     * INPUT   : applicationId (path variable), updated Application (request body)
     * RESPONSE: Updated Application object
     */
    @PutMapping("/update/{appId}")
    public String  updateApplication(
            @PathVariable int appId,
            @RequestParam("resume") MultipartFile file,
            Principal principal) throws IOException {
        
        String username = principal.getName(); //logged in user
        applicationService.updateApplication(file, username, appId);
        logger.info(" Updated application resume");
        return file.getOriginalFilename() + file.getSize(); //retuen file name
    }
    
    // ------------------------- Delete Application (Job Seeker) ---------------------------------------------
    /* FE: JOBSEEKER----> MyApplications
     * AIM     : Withdraw an application
     * PATH    : /api/applications/{id}
     * METHOD  : DELETE
     * INPUT   : applicationId (path variable)
     * RESPONSE: void
     */    
    @DeleteMapping("/delete/{appId}")
    public ResponseEntity<String> deleteApplication(@PathVariable int appId, Principal principal) {
       
        applicationService.deleteApplication(appId);
        
        logger.info("Deleting application...");
        return ResponseEntity.status(HttpStatus.OK).body("Application deleted successfully");
    }


 // ------------------------- Get Applications for-hr ---------------------------------------------
    /* FE: HR----> ApplicationList
     * AIM     : View all applications submitted by a job seeker
     * PATH    : /api/applications/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * RESPONSE: List of Application objects
     */
    @GetMapping("/for-hr")
    public List<Application> getApplicationsForHr(Principal principal) {
        String username = principal.getName();
        List<Application> application = applicationService.getApplicationsForHr(username);
        logger.info("getting applications for hr");
		return application;
    }
    
    // ------------------------- Get Applications for-js ---------------------------------------------
    /* FE: JOBSEEKER----> MyApplications
     * AIM     : View all applications submitted by a you
     * PATH    : /api/applications/jobseeker/{jobSeekerId}
     * METHOD  : GET
     * RESPONSE: List of Application objects
     */
    @GetMapping("/for-js")
    public List<Application> getApplicationsForJs(Principal principal) {
        logger.info("getting applications for hr");
        String username = principal.getName();
        List<Application> application = applicationService.getApplicationsForJs(username);
		return application;
    }
    
    // ------------------------- Get Application by ID ---------------------------------------------
    /* FE: HR----> ViewApplications
     * AIM     : Retrieve a specific application by its ID
     * PATH    : /api/applications/{id}
     * METHOD  : GET
     * INPUT   : applicationId (path variable)
     * RESPONSE: Application object or null
     */
    @GetMapping("/{id}")
    public ApplicationDTO getApplicationById(@PathVariable int id) {
        logger.info("Seeker ID given is : " + id);
        return applicationService.getApplicationById(id);
    }
    
    // -------------------------  Update Status: Reject Application (HR) ---------------------------------------------
    /* FE: HR----> MyApplications
     * AIM     : Update status to SHORTLISTED or REJECTED
     * PATH    : /api/applications/{id}/status?status=SHORTLISTED
     * METHOD  : PATCH
     * INPUT   : @PathVariable int id, @RequestParam status
     * RESPONSE: Updated Application object
     */
    @PostMapping("/update/status/{appId}")
    public ApplicationDTO updateApplicationStatus(@PathVariable int appId, 
                                                   @RequestBody ApplicationStatusDTO statusDTO) {
        logger.info("Application Status for " + appId);
        return applicationService.updateApplicationStatus(appId, statusDTO.getStatus());
    }
       

    
    
    
    
    
    
    
    
    
    
//===========================================================================================================

    
    // ------------------------- Get Applications by Job ID (HR) ---------------------------------------------
    /*
     * AIM     : Get all applications for a specific job
     * PATH    : /api/applications/job/{jobId}
     * METHOD  : GET
     * INPUT   : jobId (path variable)
     * RESPONSE: List of Application objects
     */
    @GetMapping("/jobId/{jobId}")
    public List<ApplicationDTO> getApplicationsByJobId(@PathVariable int jobId) {
    	
        logger.info("Application with Job ID : " + jobId);
        return applicationService.getApplicationsByJobId(jobId);
    }
       
    
    // ------------------------- Track Application Status JS---------------------------------------------
    /*
     * AIM     : Track status of a specific application
     * PATH    : /api/applications/status/{applicationId}
     * METHOD  : GET
     * INPUT   : applicationId (path variable)
     * RESPONSE: Application status as String or "Application not found"
     */
    @GetMapping("/status/{applicationId}")
    public Status trackStatus(@PathVariable int applicationId, Principal principal) throws AccessDeniedException { 
        // Get logged-in username
        String username = principal.getName();

        logger.info("Application Status with app. ID : " + applicationId);
        // Call service layer
        return applicationService.trackApplicationStatus(applicationId, username);
    }
    

}
