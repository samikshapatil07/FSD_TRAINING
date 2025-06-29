package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.dto.ApplicationDTO;
import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Application.Status;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private ApplicationUpdateService applicationUpdateService;

    @Autowired
    private SeekerActivityService seekerActivityService;
    @Autowired
    private JobPostingRepository jobPostingRepository;
    @Autowired
    private JobSeekerRepository jobSeekerRepository;
    
    private Logger logger = LoggerFactory.getLogger("ApplicationController");

    
    

 public ApplicationService(ApplicationRepository applicationRepository,
			ApplicationUpdateService applicationUpdateService, SeekerActivityService seekerActivityService,
			JobPostingRepository jobPostingRepository, JobSeekerRepository jobSeekerRepository) {
		super();
		this.applicationRepository = applicationRepository;
		this.applicationUpdateService = applicationUpdateService;
		this.seekerActivityService = seekerActivityService;
		this.jobPostingRepository = jobPostingRepository;
		this.jobSeekerRepository = jobSeekerRepository;
	}

//---------------------------Apply to job -------------------------------------------

public Application applyJob(MultipartFile file, String username, int jobId) throws IOException {
	// fetch jobSeeker info by username name
    JobSeeker jobSeeker = jobSeekerRepository.getJobSeekerByUsername(username);
    JobPosting jobPosting = jobPostingRepository.findById(jobId)
            .orElseThrow(() -> new RuntimeException("JobPosting not found"));

  //extension check: pdf ( only pdf allowed)
    String originalFileName = file.getOriginalFilename();
    
    String extension = originalFileName.split("\\.")[1]; //pdf
    if (!(List.of("pdf").contains(extension))) {
        throw new RuntimeException("Only PDF files allowed");
    }

	logger.info("extension approved " + extension);
	
	//check the file size
	long kbs = file.getSize() /1024;
	if(kbs > 3000) {
		logger.error("File oversized" + kbs + "kbs");
		throw new RuntimeException("Image Oversized. Max allowed size is " + kbs);
	}
	
	//check if directory exists, else create one
	String uploadFolder ="C:\\React Project\\job-portal\\public\\resumes";
	Files.createDirectories(Path.of(uploadFolder));
	logger.info(Path.of(uploadFolder) + " directory ready!!!");
	
	//define the full path
	Path path = Paths.get(uploadFolder,"\\",originalFileName);
	
	//upload file in the above path using file.copy
	Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);		//set the path i.e. resume url in author object
	
	 Application application = new Application();
	    application.setJobSeeker(jobSeeker);
	    application.setJobPosting(jobPosting);
	    application.setResumePath(originalFileName);
	    application.setStatus(Application.Status.APPLIED); 
	    application.setAppliedOn(LocalDateTime.now());

	    Application savedApp = applicationRepository.save(application);

	    // Log activity
	    seekerActivityService.logActivity(jobSeeker, ActivityType.APPLIED_JOB, "Applied to job ID: " + jobId);

	    return savedApp;

}


//---------------- Update  application and log resume changes ----------------
public void updateApplication(MultipartFile file, String username, int appId) throws IOException {
    // 1. Fetch existing application
    Application existingApp = applicationRepository.findById(appId)
            .orElseThrow(() -> new ResourceNotFoundException("Application not found"));

    // 2. Validate ownership
    String applicationOwnerUsername = existingApp.getJobSeeker().getUser().getUsername();
    if (!applicationOwnerUsername.equals(username)) {
        throw new AccessDeniedException("You are not authorized to update this application.");
    }

    // 3. Validate extension
    String originalFileName = file.getOriginalFilename();
    String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
    if (!extension.equalsIgnoreCase("pdf")) {
        throw new RuntimeException("Only PDF files allowed");
    }

    // 4. Validate size
    long kb = file.getSize() / 1024;
    if (kb > 3000) {
        throw new RuntimeException("File too large. Max allowed size is 3MB.");
    }

    // 5. Save to disk
    String uploadDir = "C:\\React Project\\job-portal\\public\\resumes";
    Files.createDirectories(Path.of(uploadDir));
    Path fullPath = Paths.get(uploadDir, originalFileName);
    Files.copy(file.getInputStream(), fullPath, StandardCopyOption.REPLACE_EXISTING);

    // 6. Update and save application
    existingApp.setResumePath(originalFileName);
    applicationRepository.save(existingApp);

    // 7. Log update
    applicationUpdateService.recordResumeUpdate(existingApp, existingApp.getJobSeeker(), originalFileName);
    seekerActivityService.logActivity(existingApp.getJobSeeker(), ActivityType.RESUME_UPDATED, "Updated resume");
}

    
 // ---------------- Delete an application  ---------------------------------------------------
    public void deleteApplication(int appId) {
        Application application = applicationRepository.findById(appId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + appId));

        // Allow delete only if status is APPLIED
        if (application.getStatus() != Application.Status.APPLIED) {
            throw new RuntimeException("You can't delete this application as its processed..");
        }
        
        applicationRepository.delete(application);
    }

    //--------------------- get applications for hr ------------------------------------------------------------
    public List<Application> getApplicationsForHr(String username) {
        return applicationRepository.getApplicationsForHr(username);
    }

    //--------------------- get applications for js ------------------------------------------------------------
    public List<Application> getApplicationsForJs(String username) {
        return applicationRepository.getApplicationsForJs(username);
    }

    
	 // ---------------- get application by ID ------------------------------------
    public ApplicationDTO getApplicationById(int applicationId) {
    Application application = applicationRepository.findById(applicationId)
   	.orElseThrow(() -> new ResourceNotFoundException("Application Id Not Found "));

    return ApplicationDTO.converttoDto(application);
}
   
  

 // ---------------- Update the status of an application ----------------
    	public ApplicationDTO updateApplicationStatus(int id, Application.Status status) {
    	    Application application = applicationRepository.findById(id)
    	            .orElseThrow(() -> new RuntimeException("Application not found"));

    	    application.setStatus(status);
    	    Application updatedApp = applicationRepository.save(application);
    	    logger.info("Status updated to: " + updatedApp.getStatus());
    	    return ApplicationDTO.converttoDto(updatedApp);
    	}
    	
	    // ------------------------- Get All Applications for Logged-in HR ---------------------------------------------
	    public List<ApplicationDTO> getApplicationsByHrUsername(String username) {
	        List<ApplicationDTO> apps = applicationRepository.findApplicationsByHrUsername(username);
	        return ApplicationDTO.converttoDto(apps);
	    }
    	
    	
    	
    	
    	
    	
    	
    	
    	
 //================================================================================================================  	
 
    	    // ---------------- Get all applications by job id -------------------------------------------
    	    public List<ApplicationDTO> getApplicationsByJobId(int jobId) {
    	        List<ApplicationDTO> applications = applicationRepository.findByJobPosting_JobId(jobId);
    	        return ApplicationDTO.converttoDto(applications);
    	    }
    	    
    	    
    	    // ---------------- Track Application Status -------------------------------
    	       public Status trackApplicationStatus(int applicationId, String username) throws AccessDeniedException {
    	           // Fetch Application entity
    	           Application application = applicationRepository.findById(applicationId)
    	               .orElseThrow(() -> new ResourceNotFoundException("Application Id Not Found"));

    	           // Fetch Job Seeker's username from Application entity
    	           String applicationOwnerUsername = application.getJobSeeker().getUser().getUsername();

    	           // Compare logged-in username with application's job seeker username
    	           if (!applicationOwnerUsername.equals(username)) {
    	               throw new AccessDeniedException("You are not authorized to view this application.");
    	           }

    	           // Return status
    	           return application.getStatus();
    	       } 
    	    


}

