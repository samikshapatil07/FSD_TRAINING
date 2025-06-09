package com.demo.CodingChallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.model.User;
import com.demo.CodingChallenge.repository.PatientRepository;
@Service
public class PatientService {
	
	
	private  UserService userService;

	@Autowired
	private PatientRepository patientRepository;
	
//-------------- register patient ----------------------
	public Patient insertPatient(Patient patient) {
		// Take user out of this learner object
				User user = patient.getUser();

				// Give role to this user
				user.setRole("PATIENT");

				// Save this User in the DB
				user = userService.signUp(user);

				// Attach this user back to patient
				patient.setUser(user);

				// Save learner in DB
				return patientRepository.save(patient);
			}
		
	}

