package com.demo.CodingChallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.dto.PatientDto;
import com.demo.CodingChallenge.service.PatientRecordService;

@RestController
@RequestMapping("/api/patient")
public class PatientRecordController {

    @Autowired
    private PatientRecordService patientRecordService;

    //------------API 4: Get patient record with medical history -------------
    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientWithHistory(@PathVariable int id) {
        PatientDto patientDto = patientRecordService.getPatientWithHistory(id);
        return ResponseEntity.ok(patientDto);
    }
}