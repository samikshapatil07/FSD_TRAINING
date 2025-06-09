package com.codingChallenge.project.service;

import com.codingChallenge.project.model.Doctor;
import com.codingChallenge.project.model.User;
import com.codingChallenge.project.model.enums.Role;
import com.codingChallenge.project.repository.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
    private final UserService userService;
    private final DoctorRepository doctorRepository;

    public DoctorService(UserService userService, DoctorRepository doctorRepository) {
        this.userService = userService;
        this.doctorRepository = doctorRepository;
    }

    /*
 //-------------   API TO ADD DOCTORS ------------------------------
     */

    public Doctor addDoctor(Doctor doctor) {

        if(doctor == null){
            throw new NullPointerException("NULL DATA CANNOT BE ACCEPTED");
        }
        if(doctor.getUser().getUsername() == null || doctor.getUser().getUsername().equals(" ")){
            throw new RuntimeException("Username Cannot be null");
        }
        if(doctor.getUser().getPassword() == null || doctor.getUser().getPassword().equals(" ")){
            throw new RuntimeException("You must enter a secure password");
        }

        User user = doctor.getUser();
        user.setRole(Role.DOCTOR);
        user = userService.signUp(user);
        doctor.setUser(user);
        return doctorRepository.save(doctor);
    }
}
