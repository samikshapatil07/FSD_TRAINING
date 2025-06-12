package com.demo.CodingChallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }
//---------------- API 1: add patient--------------------------------
	/*
     * PATH: /api/patient/add
     * Response: Patient with User details
     * PARAM: @RequestBody Patient patient
     * METHOD: POST
     * AIM: I want to add patient in Db along with its user credentails
     * so that patient can login later.
     */
    @PostMapping("/api/patient/add")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patient));
    }

}