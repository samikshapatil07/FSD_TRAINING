package com.codingChallenge.project.controller;

import com.codingChallenge.project.model.Patient;
import com.codingChallenge.project.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/api/patient/add")
    public ResponseEntity<?> addPatient(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.addPatient(patient));
    }

}
