package com.demo.CodingChallenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.service.AppointmentService;

@RestController
@RequestMapping("/api")
public class AppointmentController {

	  @Autowired
	    private AppointmentService appointmentService;
	  
	  //---------------- API 2: appointment -------------------
	  @PostMapping("/appointment")
	  public ResponseEntity<String> makeAppointment(@RequestBody Patient request) {
	        String message = appointmentService.appointment(request.getId(), request.getId());
	        return ResponseEntity.ok(message);
	    }
	
}

