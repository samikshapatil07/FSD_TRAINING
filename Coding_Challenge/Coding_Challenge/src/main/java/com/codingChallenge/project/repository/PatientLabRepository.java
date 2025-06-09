package com.codingChallenge.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import com.codingChallenge.project.model.Patient;

public interface  PatientLabRepository extends JpaRepository<Patient, Integer> {

	@Query
	("select pl.Lab.floor from PatientLab pl where pl.patient.is=?1")
	
	ResponseEntity<?> getFloorByPatient(int patientId) ;
		
	
}
