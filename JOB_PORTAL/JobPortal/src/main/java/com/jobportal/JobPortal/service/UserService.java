package com.jobportal.JobPortal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

       
//constructor
public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, HrRepository hrRepository,
			JobSeekerRepository jobSeekerRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.hrRepository = hrRepository;
		this.jobSeekerRepository = jobSeekerRepository;
	}

	//------------------------------------ Register user ------------------------------
/* This method is used when a new user signs up 
 * it takes the plain text pswd from the user obejct and encodes it using the passwordEncoder
 * pswd encryption ensures that the actual pswd is not stored in the DB
 * after encoding the user is saved in the DB using userRepository */
    public User signUp(User user) {
		// encrypt the pain text password given 
		String plainPassword = user.getPassword(); //<- this gives you plain password
		String encodedPassword =  passwordEncoder.encode(plainPassword); // encrypt the pswd
		user.setPassword(encodedPassword); //<- Now, User has encoded password 
		
		// Save User in DB 
		return userRepository.save(user);
	}
 	
    
//--------------------------- Get Logged-In User Info --------------------------------------
/* this method returns the detailed user info. based oon the role
 * first it fetches the user by username
 * then it checks if the user role and return the full object from the respective repo.
 * means if the role = hr---> return the hr object from hrRepository
 *       if the role = jd---> return the js object from jobseekerRepository
 *       if the role = rxecutive---> return null as i have not implemented taht yet*/
	public Object getUserInfo(String username) {
		User user = userRepository.findByUsername(username); //fetch the user entity by username
		
		switch (user.getRole().toUpperCase()) { // check the user role in upercase
			case "HR": //if the user is hr
				Hr hr = hrRepository.getHrByUsername(username); //fetch full HR profile
				return hr; //return hr object
				
			case "JOB_SEEKER": //if the user is job seeker
				JobSeeker jobSeeker = jobSeekerRepository.getJobSeekerByUsername(username); // fetch full JS profile
				return jobSeeker;	 //return js object
				
			case "EXECUTIVE":
				return null;
			default:
				return null; 
		}
}
}
	//------------------------------------ Get user by ID ------------------------------
//    /*to retrive a user from DB based on id
//     * to be implemented for executive*/
//	public User getUserById(int userId) {
//        return userRepository.findById(userId)
//            .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
//    }
