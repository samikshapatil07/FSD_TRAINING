package com.codingChallenge.project.service;

import com.codingChallenge.project.model.Patient;
import com.codingChallenge.project.model.User;
import com.codingChallenge.project.model.enums.Role;
import com.codingChallenge.project.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final UserService userService;
    private final PatientRepository patientRepository;

    public PatientService(UserService userService, PatientRepository patientRepository) {
        this.userService = userService;
        this.patientRepository = patientRepository;
    }
//--------------------------- add patoent -------------------------------------------
    public Patient addPatient(Patient patient) {

        if(patient == null){
            throw new NullPointerException("NULL DATA CANNOT BE ACCEPTED");
        }
        if(patient.getUser().getUsername() == null || patient.getUser().getUsername().equals(" "))
        
        {
            throw new RuntimeException("Username Cannot be null");
        }
        if(patient.getUser().getPassword() == null || patient.getUser().getPassword().equals(" "))
        {
            throw new RuntimeException("You must enter a secure password");
        }

        User user = patient.getUser();
        user.setRole(Role.PATIENT);
        user = userService.signUp(user);
        patient.setUser(user);
        return patientRepository.save(patient);
    }

  //---------------- get patent by username --------------------

    public Patient getByUsername(String username) {
        return patientRepository.getByUsername(username);
    }
}
