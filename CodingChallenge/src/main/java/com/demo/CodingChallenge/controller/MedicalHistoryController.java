package com.demo.CodingChallenge.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.MedicalHistory;
import com.demo.CodingChallenge.service.MedicalHistoryService;



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