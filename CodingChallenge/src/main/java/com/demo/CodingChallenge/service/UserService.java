package com.demo.CodingChallenge.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.Doctor;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.model.User;
import com.demo.CodingChallenge.repository.DoctorRepository;
import com.demo.CodingChallenge.repository.PatientRepository;
import com.demo.CodingChallenge.repository.UserRepository;


@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private PatientRepository patientRepository;
	private DoctorRepository doctorRepository;

	
public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			PatientRepository patientRepository, DoctorRepository doctorRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
	}

	//-------------------------sign up user---------------------
	public User signUp(User user) {
		// encrypt the pain text password given
		String plainPassword = user.getPassword(); // <- this gives you plain password
		String encodedPassword = passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword); // <- Now, User has encoded password
         //check if user exists
		if (userRepository.findByUsername(user.getUsername()) != null) {
		    throw new RuntimeException("Username already exists");
		}
		// Save User in DB
		return userRepository.save(user);
	}

//--------------------get user info-------------------
	public Object getUserInfo(String username) {
		User user = userRepository.findByUsername(username);
		switch (user.getRole().toUpperCase()) {
			case "DOCTOR":
				Doctor doctor = doctorRepository.getDoctorByUsername(username);
				return doctor;
			case "PATIENT":
				Patient patient = patientRepository.getPatientByUsername(username);
				return patient;			
			default:
				return null;
		}
	}
}