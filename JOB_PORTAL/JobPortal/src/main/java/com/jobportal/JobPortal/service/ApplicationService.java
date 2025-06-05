package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.dto.ApplicationDTO;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

 // ---------------- Save Application -----------------------------------------------
    public ApplicationDTO  saveApplication(int seekerId, int jobId, ApplicationDTO applicationDTO) {
        JobSeeker jobSeeker = jobSeekerRepository.findById(seekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found with ID: " + seekerId));

        JobPosting jobPosting = jobPostingRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("JobPosting not found with ID: " + jobId));

        Application application = new Application();
        application.setJobSeeker(jobSeeker);
        application.setJobPosting(jobPosting);
        application.setResumePath(applicationDTO.getResumePath());

        Application savedApp = applicationRepository.save(application);

        seekerActivityService.logActivity(
                jobSeeker,
                ActivityType.APPLIED_JOB,
                "Applied to job ID: " + jobId
        );

        ApplicationDTO  dto = new ApplicationDTO ();
        dto.setApplicationId(savedApp.getApplicationId());
        dto.setJobSeekerId(seekerId);
        dto.setJobId(jobId);
        dto.setResumePath(savedApp.getResumePath());
        dto.setStatus(savedApp.getStatus().toString());
        dto.setAppliedOn(savedApp.getAppliedOn());

        return dto;
    }

 // ---------------- get application by ID ------------------------------------
    public Optional<ApplicationDTO> getApplicationResponseById(int id) {
        Optional<Application> optionalApp = applicationRepository.findById(id);
        if (optionalApp.isPresent()) {
            Application app = optionalApp.get();
            ApplicationDTO dto = new ApplicationDTO();
            dto.setApplicationId(app.getApplicationId());
            dto.setJobSeekerId(app.getJobSeeker().getJobSeekerId());
            dto.setJobId(app.getJobPosting().getJobId());
            dto.setResumePath(app.getResumePath());
            dto.setStatus(app.getStatus().toString());
            dto.setAppliedOn(app.getAppliedOn());
            return Optional.of(dto);
        } else {
            return Optional.empty();
        }
    }

    // ---------------- Get all applications submitted by a specific job seeker ----------------

    public List<ApplicationDTO> getApplicationsByJobSeekerId(int jobSeekerId) {
        List<Application> applications = applicationRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
        List<ApplicationDTO> dtoList = new ArrayList<>();

        for (Application app : applications) {
        	ApplicationDTO dto = new ApplicationDTO();
            dto.setApplicationId(app.getApplicationId());
            dto.setJobSeekerId(app.getJobSeeker().getJobSeekerId());
            dto.setJobId(app.getJobPosting().getJobId());
            dto.setResumePath(app.getResumePath());
            dto.setStatus(app.getStatus().toString());
            dto.setAppliedOn(app.getAppliedOn());
            dtoList.add(dto);
        }

        return dtoList;
    }

    // ---------------- Get all applications by job id ----------------
    public List<ApplicationDTO> getApplicationsByJobId(int jobId) {
        List<Application> applications = applicationRepository.findByJobPosting_JobId(jobId);
        List<ApplicationDTO> dtoList = new ArrayList<>();

        for (Application app : applications) {
        	ApplicationDTO dto = new ApplicationDTO();
            dto.setApplicationId(app.getApplicationId());
            dto.setJobSeekerId(app.getJobSeeker().getJobSeekerId());
            dto.setJobId(app.getJobPosting().getJobId());
            dto.setResumePath(app.getResumePath());
            dto.setStatus(app.getStatus().toString());
            dto.setAppliedOn(app.getAppliedOn());
            dtoList.add(dto);
        }

        return dtoList;
    }

    // ---------------- Get all applications ----------------
    public List<ApplicationDTO> getAllApplications() {
        List<Application> applications = applicationRepository.findAll();
        List<ApplicationDTO> dtoList = new ArrayList<>();

        for (Application app : applications) {
        	ApplicationDTO dto = new ApplicationDTO();
            dto.setApplicationId(app.getApplicationId());
            dto.setJobSeekerId(app.getJobSeeker().getJobSeekerId());
            dto.setJobId(app.getJobPosting().getJobId());
            dto.setResumePath(app.getResumePath());
            dto.setStatus(app.getStatus().toString());
            dto.setAppliedOn(app.getAppliedOn());
            dtoList.add(dto);
        }

        return dtoList;
    }
    // ---------------- Update an existing application and log resume changes ----------------
    public ApplicationDTO updateApplication(int id, ApplicationDTO updatedDTO) {
        Optional<Application> optionalApp = applicationRepository.findById(id);

        if (optionalApp.isPresent()) {
            Application existingApp = optionalApp.get();

            String oldResume = existingApp.getResumePath();
            String newResume = updatedDTO.getResumePath();

            if (newResume != null && !newResume.equals(oldResume)) {
                existingApp.setResumePath(newResume);

                JobSeeker jobSeeker = existingApp.getJobSeeker();
                if (jobSeeker != null) {
                    applicationUpdateService.recordResumeUpdate(existingApp, jobSeeker, newResume);

                    seekerActivityService.logActivity(
                            jobSeeker,
                            ActivityType.RESUME_UPDATED,
                            "Updated resume to: " + newResume
                    );
                }
            }

            Application updatedApp = applicationRepository.save(existingApp);

            ApplicationDTO dto = new ApplicationDTO();
            dto.setApplicationId(updatedApp.getApplicationId());
            dto.setJobSeekerId(updatedApp.getJobSeeker().getJobSeekerId());
            dto.setJobId(updatedApp.getJobPosting().getJobId());
            dto.setResumePath(updatedApp.getResumePath());
            dto.setStatus(updatedApp.getStatus().toString());
            dto.setAppliedOn(updatedApp.getAppliedOn());

            return dto;
        } else {
            throw new RuntimeException("Application not found with ID: " + id);
        }
    }

    // ---------------- Delete an application by its ID ----------------
    public void deleteApplication(int id) {
        applicationRepository.deleteById(id);
    }

    // ---------------- Update the status of an application ----------------
    public ApplicationDTO updateApplicationStatus(int id, Application.Status status) {
        Optional<Application> optionalApp = applicationRepository.findById(id);

        if (optionalApp.isPresent()) {
            Application application = optionalApp.get();
            application.setStatus(status);
            Application updatedApp = applicationRepository.save(application);

            ApplicationDTO dto = new ApplicationDTO();
            dto.setApplicationId(updatedApp.getApplicationId());
            dto.setJobSeekerId(updatedApp.getJobSeeker().getJobSeekerId());
            dto.setJobId(updatedApp.getJobPosting().getJobId());
            dto.setResumePath(updatedApp.getResumePath());
            dto.setStatus(updatedApp.getStatus().toString());
            dto.setAppliedOn(updatedApp.getAppliedOn());

            return dto;
        } else {
            throw new RuntimeException("Application not found with ID: " + id);
        }
    }
}
