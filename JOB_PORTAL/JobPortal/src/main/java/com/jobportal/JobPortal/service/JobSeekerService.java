package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.JobSeeker;
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

	public JobSeekerService(JobSeekerRepository jobSeekerRepository, UserService userService) {
		super();
		this.jobSeekerRepository = jobSeekerRepository;
		this.userService = userService;
	}

	// ------------------------- Registers a Job Seeker ------------------
	public JobSeeker registerjobSeeker(JobSeeker jobSeeker) {
		// Take user out of this learner object
		User user = jobSeeker.getUser();

		// Give role to this user
		user.setRole("JOB_SEEKER");

		// Save this user using userService
		user = userService.signUp(user);

		// Attach user back to HR
		jobSeeker.setUser(user);

		// Save HR in the database
		return jobSeekerRepository.save(jobSeeker);
	}

//---------------- Get a Job Seeker by ID -----------------------
	public JobSeeker getJobSeekerById(Long jobSeekerId) {
		return jobSeekerRepository.findById(jobSeekerId)
				.orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));
	}

//------------------------ Updates job seeker ----------------------
	public JobSeeker updateJobSeeker(Long jobSeekerId, JobSeeker updated) {
		// Check if the job seeker exists
		JobSeeker existing = jobSeekerRepository.findById(jobSeekerId)
				.orElseThrow(() -> new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId));
		// Update
		existing.setName(updated.getName());
		existing.setEducation(updated.getEducation());
		existing.setSkills(updated.getSkills());
		existing.setExperience(updated.getExperience());

		return jobSeekerRepository.save(existing);
	}

//---------------------------  Deletes  job seeker by ID----------------  
	public void deleteJobSeeker(Long jobSeekerId) {
		if (!jobSeekerRepository.existsById(jobSeekerId)) {
			throw new ResourceNotFoundException("Job Seeker not found with ID: " + jobSeekerId);
		}
		jobSeekerRepository.deleteById(jobSeekerId);
	}

//---------------------------  Get all job seekar ----------------  

	public List<JobSeeker> getAllJobSeekers() {
		return jobSeekerRepository.findAll();
	}
}
