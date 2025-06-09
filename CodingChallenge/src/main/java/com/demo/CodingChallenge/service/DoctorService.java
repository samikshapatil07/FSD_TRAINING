package com.demo.CodingChallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.Doctor;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.repository.DoctorRepository;

@Service
public class DoctorService {

	@Autowired
    private  DoctorRepository doctorRepository;

	public DoctorService(DoctorRepository doctorRepository) {
		super();
		this.doctorRepository = doctorRepository;
	}
	
//-------------------- API :3 -get patient by doctor ID-----------------

	public List<Patient> getPatientsByDoctorId(int doctorId) {
		Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return doctor.getPatients();
	}
}
