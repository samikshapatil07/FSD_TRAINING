package com.demo.CodingChallenge.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.model.PatientLab;

@Repository
public interface  PatientLabRepository extends JpaRepository<PatientLab, Integer> {

	 @Query("SELECT pl.lab.floor FROM PatientLab pl WHERE pl.patient.id = ?1")
	    List<Integer> getFloorByPatient(int patientId);
	
}