package com.demo.CodingChallenge.service;

import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.model.Role;
import com.demo.CodingChallenge.model.User;
import com.demo.CodingChallenge.repository.PatientRepository;
@Service
public class PatientService {

    private final UserService userService;
    private final PatientRepository patientRepository;

    public PatientService(UserService userService, PatientRepository patientRepository) {
        this.userService = userService;
        this.patientRepository = patientRepository;
    }
    /*
    API TO add Patient
     */
    public Patient addPatient(Patient patient) {

        if(patient == null){
            throw new NullPointerException("NULL DATA CANNOT BE ACCEPTED");
        }
        if(patient.getUser().getUsername() == null || patient.getUser().getUsername().equals(" ")){
            throw new RuntimeException("Username Cannot be null");
        }
        if(patient.getUser().getPassword() == null || patient.getUser().getPassword().equals(" ")){
            throw new RuntimeException("You must enter a secure password");
        }

        User user = patient.getUser();
        user.setRole(Role.PATIENT);
        user = userService.signUp(user);
        patient.setUser(user);
        return patientRepository.save(patient);
    }

    /*
    Getting patient by username
     */

    public Patient getByUsername(String username) {
        return patientRepository.getPatientByUsername(username);
    }
}
		
	

