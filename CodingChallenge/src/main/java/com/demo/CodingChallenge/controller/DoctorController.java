package com.demo.CodingChallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {

	 @Autowired
	    private DoctorService doctorService;
	 
//--------API 3: ------------------------------	   
	 @GetMapping("/patients/{doctorId}")
	    public ResponseEntity<List<Patient>> getPatientsByDoctorId(@PathVariable int doctorId) {
	        List<Patient> patients = doctorService.getPatientsByDoctorId(doctorId);
	        return ResponseEntity.ok(patients);
	    }
}


