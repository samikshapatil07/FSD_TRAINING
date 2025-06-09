package com.demo.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.CodingChallenge.model.Doctor;

public interface  DoctorRepository extends JpaRepository<Doctor, Integer>{

	// jpql to get doctor by user name
	@Query("select d from Doctor d where d.user.username=?1")
	Doctor getDoctorByUsername(String username);
	
}


