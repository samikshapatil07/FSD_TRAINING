package com.demo.CodingChallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.service.PatientService;

@RestController
@RequestMapping("/api")
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
//---------------- API 1: add patient--------------------------------
	/*
     * PATH: /api/patient/add
     * Response: Patient with User details
     * PARAM: @RequestBody Patient patient
     * METHOD: POST
     * AIM: I want to add patient in Db along with its user credentails
     * so that patient can login later.
     */
	@PostMapping("/patient/add")
	public ResponseEntity<Patient> insertPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.insertPatient(patient);
        return ResponseEntity.ok(savedPatient);
    }
}