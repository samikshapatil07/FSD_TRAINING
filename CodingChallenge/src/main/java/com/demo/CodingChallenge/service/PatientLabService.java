package com.demo.CodingChallenge.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.repository.PatientLabRepository;

@Service
public class PatientLabService {

	@Autowired
	private PatientLabRepository patientLabRepository;

	public PatientLabService(PatientLabRepository patientLabRepository) {
		super();
		this.patientLabRepository = patientLabRepository;
	}

	public List<Integer> getFloorByPatient(int patientId){
		return patientLabRepository.getFloorByPatient(patientId);
	}
}