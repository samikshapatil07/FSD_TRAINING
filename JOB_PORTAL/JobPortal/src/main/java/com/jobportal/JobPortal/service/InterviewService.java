package com.jobportal.JobPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.InterviewRepository;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository repository;

    @Autowired
    private ApplicationRepository applicationRepository;
    
    //------------------------- schedule interview for application (HR)----------------------------------------
    public Interview scheduleInterview(Interview interview) {
        // Load the full application from DB
        Long appId = interview.getApplication().getApplicationId();
        Application application = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application not found with ID: " + appId));

        // Attach it
        interview.setApplication(application);

        // Save interview
        Interview saved = repository.save(interview);

        // Update application status
        application.setStatus(Application.Status.INTERVIEW_SCHEDULED);
        applicationRepository.save(application);

        return saved;
    }
    
    //----------------------------- Updates interview details(Hr) ------------------------------------------
    public Interview updateInterview(Interview interview) {
        Interview existing = repository.findById(interview.getInterviewId())
            .orElseThrow(() -> new RuntimeException("Interview not found"));

        // Update only details, not outcome
        existing.setInterviewDate(interview.getInterviewDate());
        existing.setInterviewLocation(interview.getInterviewLocation());
        existing.setInterviewMode(interview.getInterviewMode());

        return repository.save(existing);
    }
//------------------------ Updates the outcome of an interview ---------------------------
    public Interview updateInterviewStatus(Integer interviewId, Interview.InterviewOutcome outcome) {
        Interview interview = repository.findById(interviewId)
            .orElseThrow(() -> new RuntimeException("Interview not found"));

        interview.setOutcome(outcome);
        Interview updated = repository.save(interview);

        // Sync application status
        Application app = interview.getApplication();
        if (outcome == Interview.InterviewOutcome.INTERVIEW_COMPLETED_OFFERED) {
            app.setStatus(Application.Status.INTERVIEW_COMPLETED_OFFERED);
        } else if (outcome == Interview.InterviewOutcome.INTERVIEW_COMPLETED_REJECTED) {
            app.setStatus(Application.Status.INTERVIEW_COMPLETED_REJECTED);
        }
        applicationRepository.save(app);

        return updated;
    }

    //-------------------- get interview by application id -----------------------
    public List<Interview> getInterviewsByApplicationId(Integer appId) {
        return repository.findByApplicationApplicationId(appId);
    }
    
    //-------------------- get interview by interview  id -----------------------
    public Interview getInterviewById(Integer interviewId) {
        return repository.findById(interviewId).orElse(null);
    }
    
    //-------------------- delete interview -----------------------
    public void deleteInterview(Integer interviewId) {
        repository.deleteById(interviewId);
    }
}
