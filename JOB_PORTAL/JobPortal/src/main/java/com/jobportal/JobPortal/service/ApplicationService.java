package com.jobportal.JobPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;

@Service
public class ApplicationService {

	@Autowired
	private JobPostingRepository jobPostingRepository;

	@Autowired
	private JobSeekerRepository jobSeekerRepository;
	
    @Autowired
    private ApplicationRepository applicationRepository;

    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }

    public Optional<Application> getApplicationById(Long id) {
        return applicationRepository.findById(id);
    }

    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    public List<Application> getApplicationsByJobSeekerId(Long jobSeekerId) {
        return applicationRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
    }

    public List<Application> getApplicationsByJobId(Long jobId) {
        return applicationRepository.findByJobPosting_JobId(jobId);
    }

    
    public Application updateApplicationStatus(Long id, Application.Status newStatus) {
        Optional<Application> applicationOpt = applicationRepository.findById(id);
        if (applicationOpt.isPresent()) {
            Application application = applicationOpt.get();
            Application.Status currentStatus = application.getStatus();

            if (isValidStatusTransition(currentStatus, newStatus)) {
                application.setStatus(newStatus);
                return applicationRepository.save(application);
            } else {
                throw new IllegalArgumentException("Invalid status transition from " + currentStatus + " to " + newStatus);
            }
        }
        return null;
    }

    private boolean isValidStatusTransition(Application.Status current, Application.Status next) {
        switch (current) {
            case APPLIED:
                return next == Application.Status.SHORTLISTED || next == Application.Status.REJECTED;
            case SHORTLISTED:
                return next == Application.Status.INTERVIEW_SCHEDULED;
            case INTERVIEW_SCHEDULED:
                return next == Application.Status.INTERVIEW_COMPLETED;
            case INTERVIEW_COMPLETED:
                return next == Application.Status.OFFERED || next == Application.Status.REJECTED;
            
            default:
                return false;
        }
    }

}
