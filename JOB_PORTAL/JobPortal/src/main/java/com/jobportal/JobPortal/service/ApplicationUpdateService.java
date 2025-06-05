package com.jobportal.JobPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.ApplicationUpdate;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.repository.ApplicationUpdateRepository;

@Service
public class ApplicationUpdateService {

    @Autowired
    private ApplicationUpdateRepository repository;
    
//---------------------- Saves a new application update ------------------------------------
    public ApplicationUpdate createUpdate(ApplicationUpdate update) {
        return repository.save(update);
    }
    
//-------------------- get application update by app. id
    public List<ApplicationUpdate> getUpdatesByApplicationId(Integer applicationId) {
        return repository.findByApplicationApplicationId(applicationId);
    }

//--------------------get application update by job seeker id
    public List<ApplicationUpdate> getUpdatesByJobSeekerId(Integer jobSeekerId) {
        return repository.findByJobSeekerJobSeekerId(jobSeekerId);
    }
//----------------------------- Records a resume update made by a job seeker for a specific application ----------------
    public void recordResumeUpdate(Application app, JobSeeker jobSeeker, String resumePath) {
        ApplicationUpdate update = new ApplicationUpdate();
        update.setApplication(app);
        update.setJobSeeker(jobSeeker);
        update.setUpdatedResumePath(resumePath);
        repository.save(update);
    }
}
