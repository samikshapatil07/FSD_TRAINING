package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;
import com.jobportal.JobPortal.repository.ApplicationRepository;
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

 // ---------------- Save a new application -----------------------------------------------
    public Application saveApplication(Application application) {
        Application savedApp = applicationRepository.save(application);

        // Log activity
        JobSeeker seeker = savedApp.getJobSeeker();
        if (seeker != null) {
            seekerActivityService.logActivity(
                seeker,
                ActivityType.APPLIED_JOB,
                "Applied to job ID: " + savedApp.getJobPosting().getJobId()
            );
        }

        return savedApp;
    }

 // ---------------- Get an application by its ID ------------------------------------
    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    // ---------------- Get all applications submitted by a specific job seeker ----------------

    public List<Application> getApplicationsByJobSeekerId(Long jobSeekerId) {
        return applicationRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
    }

    // ---------------- Get all applications for a specific job posting ----------------
    public List<Application> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobPosting_JobId(jobId);
    }

    // ---------------- Get all applications ----------------
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    // ---------------- Update an existing application and log resume changes ----------------
    public Application updateApplication(Long id, Application updatedApplication) {
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
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    // ---------------- Update the status of an application ----------------
    public Application updateApplicationStatus(Long id, Application.Status status) {
        return applicationRepository.findById(id).map(application -> {
            application.setStatus(status);
            return applicationRepository.save(application);
        }).orElseThrow(() -> new RuntimeException("Application not found with ID: " + id));
    }
}
