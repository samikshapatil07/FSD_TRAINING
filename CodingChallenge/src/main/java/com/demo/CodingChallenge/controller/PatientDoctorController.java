package com.demo.CodingChallenge.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.PatientDoctor;
import com.demo.CodingChallenge.service.PatientDoctorService;

@RestController
public class PatientDoctorController {

    private final PatientDoctorService patientDoctorService;

    public PatientDoctorController(PatientDoctorService patientDoctorService) {
        this.patientDoctorService = patientDoctorService;
    }

    @PostMapping("/api/patient/doctor/appointment/add")
    public ResponseEntity<?> createAppointment(@RequestParam int patientId, @RequestParam int doctorId, @RequestBody PatientDoctor patientDoctor){
        return ResponseEntity.status(HttpStatus.CREATED).body(patientDoctorService.createAppointment(patientId, doctorId, patientDoctor));
    }
    @GetMapping("/api/patient/doctor/getPatients")
    public ResponseEntity<?> getPatientsByDoctor(@RequestParam int doctorId){
        return ResponseEntity.status(HttpStatus.FOUND).body(patientDoctorService.getPatientsByDoctor(doctorId));
    }

}