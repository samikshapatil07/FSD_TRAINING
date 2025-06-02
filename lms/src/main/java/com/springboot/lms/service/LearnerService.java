
package com.springboot.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.lms.model.Learner;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.LearnerRepository;
@Service
public class LearnerService {

	private final LearnerRepository learnerRepository;
	private final UserService userService;
	
	public LearnerService(LearnerRepository learnerRepository, UserService userService) {
		this.learnerRepository = learnerRepository;
		this.userService = userService;
	}

	public Learner insertLearner(Learner learner) { 
		// Take user out of this learner object 
		User user = learner.getUser();
		
		// Give role to this user 
		user.setRole("LEARNER");
		
		// Save this User in the DB 
		user = userService.signUp(user);
		
		// Attach this user back to learner
		learner.setUser(user);
		
		// Save learner in DB
		return learnerRepository.save(learner);
	}

	public List<Learner> getAll() { 
		return learnerRepository.findAll();
	}

	public void deleteLearner(int id) {
		learnerRepository.deleteById(id);
		
	}

	public Learner getLearnerById(int id) {
		return learnerRepository.findById(id)
				.orElseThrow(()->new RuntimeException("ID is Invalid"));
	}

	public Learner updateLearner(int id, Learner updatedLearner) {
		/*Step 1: validate the ID and fetch learner record from DB */
		Learner dbLearner =  learnerRepository.findById(id)
		.orElseThrow(()->new RuntimeException("Invalid ID Given")); 
		
		if(updatedLearner.getName() != null)
			dbLearner.setName(updatedLearner.getName());
		if(updatedLearner.getContact() != null)
			dbLearner.setContact(updatedLearner.getContact());
		
		return learnerRepository.save(dbLearner);
	}

	public Learner getLearnerByUsername(String username) {
		return learnerRepository.getLearnerByUsername(username);
	}

}
