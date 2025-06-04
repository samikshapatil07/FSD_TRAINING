package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

 // ---------------- save application -----------------------------------------------
    public Application saveApplication(int seekerId, int jobId, Application application) {
        // Validate JobSeeker
        JobSeeker jobSeeker = jobSeekerRepository.findById((int) seekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with ID: " + seekerId));

        // Validate JobPosting
        JobPosting jobPosting = jobPostingRepository.findById((int) jobId)
                .orElseThrow(() -> new RuntimeException("JobPosting not found with ID: " + jobId));

        // Attach to application 
        application.setJobSeeker(jobSeeker);
        application.setJobPosting(jobPosting);

        // Save application
        Application savedApp = applicationRepository.save(application);

        // Log activity
        seekerActivityService.logActivity(
            jobSeeker,
            ActivityType.APPLIED_JOB,
            "Applied to job ID: " + jobId
        );

        return savedApp;
    }
 // ---------------- get application by ID ------------------------------------
    public Optional<Application> getApplicationById(int id) {
        return applicationRepository.findById(id);
    }

    // ---------------- Get all applications submitted by a specific job seeker ----------------

    public List<Application> getApplicationsByJobSeekerId(int jobSeekerId) {
        return applicationRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
    }

    // ---------------- Get all applications by job id ----------------
    public List<Application> getApplicationsByJobId(int jobId) {
        return applicationRepository.findByJobPosting_JobId(jobId);
    }

    // ---------------- Get all applications ----------------
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // ---------------- Update an existing application and log resume changes ----------------
    public Application updateApplication(int id, Application updatedApplication) {
        return applicationRepository.findById(id).map(existingApp -> {
            String oldResume = existingApp.getResumePath();
            String newResume = updatedApplication.getResumePath();

            if (newResume != null && !newResume.equals(oldResume)) {
                existingApp.setResumePath(newResume);

                JobSeeker jobSeeker = existingApp.getJobSeeker();
                if (jobSeeker != null) {
                    applicationUpdateService.recordResumeUpdate(existingApp, jobSeeker, newResume);

                    // Log resume update activity
                    seekerActivityService.logActivity(
                        jobSeeker,
                        ActivityType.RESUME_UPDATED,
                        "Updated resume to: " + newResume
                    );
                }
            }

            return applicationRepository.save(existingApp);
        }).orElseThrow(() -> new RuntimeException("Application not found with ID: " + id));
    }

    // ---------------- Delete an application by its ID ----------------
    public void deleteApplication(int id) {
        applicationRepository.deleteById(id);
    }

    // ---------------- Update the status of an application ----------------
    public Application updateApplicationStatus(int id, Application.Status status) {
        return applicationRepository.findById(id).map(application -> {
            application.setStatus(status);
            return applicationRepository.save(application);
        }).orElseThrow(() -> new RuntimeException("Application not found with ID: " + id));
    }
}
