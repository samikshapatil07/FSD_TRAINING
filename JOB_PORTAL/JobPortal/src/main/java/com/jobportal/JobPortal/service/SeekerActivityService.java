package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.SeekerActivity;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;
import com.jobportal.JobPortal.repository.SeekerActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeekerActivityService {

    @Autowired
    private SeekerActivityRepository repository;
//----------------------Logs a new activity performed by a job seeker ---------------

    public void logActivity(JobSeeker jobSeeker, ActivityType activityType, String description) {
        SeekerActivity activity = new SeekerActivity();
        activity.setJobSeeker(jobSeeker);
        activity.setActivityType(activityType);
        activity.setDescription(description);
        repository.save(activity);
    }
//---------------Retrieves all activities performed by a specific js by id ---------------------
    public List<SeekerActivity> getActivitiesByJobSeekerId(Long jobSeekerId) {
        return repository.findByJobSeeker_JobSeekerId(jobSeekerId);
    }
//-------------------------Retrieves all seeker activities---------------------
    public List<SeekerActivity> getAllActivities() {
        return repository.findAll();
    }
}
