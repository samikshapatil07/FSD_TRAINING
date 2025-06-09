package com.codingChallenge.project.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingChallenge.project.service.PatientLabService;

@RestController
@RequestMapping("/api/patientlab")
public class PatientLabController {
	
	
	private PatientLabService patientLabSErvice;
	@GetMapping("/getfloor/{patientId}")
	public ResponseEntity<?> getFloorByPatient(@PathVariable int patientId){
        return patientLabSErvice.getgetFloorByPatient(patientId);
    }

}
