package com.demo.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.CodingChallenge.model.Doctor;

public interface  DoctorRepository extends JpaRepository<Doctor, Integer>{
	
}


