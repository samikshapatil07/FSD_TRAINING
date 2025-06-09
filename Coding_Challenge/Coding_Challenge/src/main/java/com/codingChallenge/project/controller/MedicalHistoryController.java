package com.codingChallenge.project.controller;

import com.codingChallenge.project.model.MedicalHistory;
import com.codingChallenge.project.model.Patient;
import com.codingChallenge.project.service.MedicalHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryController(MedicalHistoryService medicalHistoryService) {
        this.medicalHistoryService = medicalHistoryService;
    }

    @PostMapping("/api/patient/medicalHistory/add")
    public ResponseEntity<?> addPatientWithMedicalRecords(@RequestBody MedicalHistory medicalHistory){
        return ResponseEntity.status(HttpStatus.CREATED).body(medicalHistoryService.addPatientWithMedicalRecords(medicalHistory));
    }

}
