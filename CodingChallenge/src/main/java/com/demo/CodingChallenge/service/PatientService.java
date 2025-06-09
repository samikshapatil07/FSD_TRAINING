package com.demo.CodingChallenge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.MedicalHistory;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.model.User;
import com.demo.CodingChallenge.repository.MedicalHistoryRepository;
import com.demo.CodingChallenge.repository.PatientRepository;
@Service
public class PatientService {
	
	@Autowired 
	private  UserService userService;

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private MedicalHistoryRepository medicalHistoryRepository;
	
    private  PasswordEncoder passwordEncoder;

    
public PatientService(UserService userService, PatientRepository patientRepository,
			MedicalHistoryRepository medicalHistoryRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userService = userService;
		this.patientRepository = patientRepository;
		this.medicalHistoryRepository = medicalHistoryRepository;
		this.passwordEncoder = passwordEncoder;
	}



//-------------- API: 1 - register patient ----------------------
	public Patient insertPatient(Patient patient) {
		// Take user out of this learner object
				User user = patient.getUser();
				// Give role to this user
				user.setRole("PATIENT");
				// Save this User in the DB
				user = userService.signUp(user);

				// Attach this user back to patient
			    patient.setName(patient.getName());
			    patient.setAge(patient.getAge());
				patient.setUser(user);

				// Save Medical History
			    if (patient.getMedicalHistories() != null) {
			        for (MedicalHistory history : patient.getMedicalHistories()) {
			            history.setPatient(patient);
			            medicalHistoryRepository.save(history);
			        }
			    }
			 // Save patient in DB
				return patientRepository.save(patient);
			    }
}

	
		
	

