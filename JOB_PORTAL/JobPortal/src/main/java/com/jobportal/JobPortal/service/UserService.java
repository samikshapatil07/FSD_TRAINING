package com.jobportal.JobPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.HrRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;
import com.jobportal.JobPortal.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private HrRepository hrRepository;
    private JobSeekerRepository jobSeekerRepository;

    
    

public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, HrRepository hrRepository,
			JobSeekerRepository jobSeekerRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.hrRepository = hrRepository;
		this.jobSeekerRepository = jobSeekerRepository;
	}

	//------------------------------------ Register user ------------------------------
    public User signUp(User user) {
		// encrypt the pain text password given 
		String plainPassword = user.getPassword(); //<- this gives you plain password
		String encodedPassword =  passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword); //<- Now, User has encoded password 
		
		// Save User in DB 
		return userRepository.save(user);
	}
 	
//------------------------------------ Get user by ID ------------------------------
    public User getUserById(int userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }
    

	public Object getUserInfo(String username) {
		User user = userRepository.findByUsername(username);
		switch (user.getRole().toUpperCase()) {
			case "HR":
				Hr hr = hrRepository.getByUsername(username);
				return hr;
			case "JOB_SEEKER":
				JobSeeker jobSeeker = jobSeekerRepository.getJobSeekerByUsername(username);
				if(jobSeeker.isActive())
				return jobSeeker;
				else
					throw new RuntimeException("JobSeeker Inactive");			
				case "EXECUTIVE":
				return null;
			default:
				return null;
		}
}
}