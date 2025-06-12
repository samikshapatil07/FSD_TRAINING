package com.demo.CodingChallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.service.PatientLabService;

@RestController
@RequestMapping("/api/patientlab")
public class PatientLabController {
	
	@Autowired
	private PatientLabService patientLabService;
	
	@GetMapping("/getfloor/{patientId}")
	public List<Integer> getFloorByPatient(@PathVariable int patientId){
        return patientLabService.getFloorByPatient(patientId);
    }

}