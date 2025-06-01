package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.JobSeekerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobSeekerService {

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SeekerActivityService seekerActivityService;

    public JobSeekerService(JobSeekerRepository jobSeekerRepository, UserService userService) {
        super();
        this.jobSeekerRepository = jobSeekerRepository;
        this.userService = userService;
    }

    // ------------------------- Registers a Job Seeker ------------------
    public JobSeeker registerjobSeeker(JobSeeker jobSeeker) {
        User user = jobSeeker.getUser();
        user.setRole("JOB_SEEKER");
        user = userService.signUp(user);
        jobSeeker.setUser(user);
        return jobSeekerRepository.save(jobSeeker);
    }

    //---------------- Get a Job Seeker by ID -----------------------
    public JobSeeker getJobSeekerById(Long jobSeekerId) {
        return jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));
    }

    //------------------------ Updates job seeker ----------------------
    public JobSeeker updateJobSeeker(Long jobSeekerId, JobSeeker updated) {
        JobSeeker existing = jobSeekerRepository.findById(jobSeekerId)
                .orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));

        existing.setName(updated.getName());
        existing.setEducation(updated.getEducation());
        existing.setSkills(updated.getSkills());
        existing.setExperience(updated.getExperience());

        JobSeeker saved = jobSeekerRepository.save(existing);

        // Log activity
        seekerActivityService.logActivity(saved, ActivityType.PROFILE_UPDATED, "Updated job seeker profile");

        return saved;
    }

    //--------------------------- Deletes job seeker by ID ----------------  
    public void deleteJobSeeker(Long jobSeekerId) {
        if (!jobSeekerRepository.existsById(jobSeekerId)) {
            throw new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId);
        }
        jobSeekerRepository.deleteById(jobSeekerId);
    }

    //--------------------------- Get all job seekers ----------------  
    public List<JobSeeker> getAllJobSeekers() {
        return jobSeekerRepository.findAll();
    }
}
