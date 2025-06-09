package com.demo.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.CodingChallenge.model.Patient;


public interface PatientRepository extends JpaRepository<Patient, Integer>{

	// jpql to get patient by user name
	@Query("select p from Patient p where p.user.username=?1")
	Patient getPatientByUsername(String username);

}
