package com.codingChallenge.project.service;

import org.springframework.http.ResponseEntity;

import com.codingChallenge.project.repository.PatientLabRepository;

public class PatientLabService {

	
	private PatientLabRepository patientLabRepository;

	public PatientLabService(PatientLabRepository patientLabRepository) {
		super();
		this.patientLabRepository = patientLabRepository;
	}

	public ResponseEntity<?>  getgetFloorByPatient(int patientId){
		return patientLabRepository.getFloorByPatient(patientId);
	}
}
