package com.springboot.lms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.lms.model.Learner;
import com.springboot.lms.repository.LearnerRepository;
@Service
public class LearnerService {

	private LearnerRepository learnerRepository;

	public LearnerService(LearnerRepository learnerRepository) {
		this.learnerRepository = learnerRepository;
	}

	public Learner insertLearner(Learner learner) { 
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

}