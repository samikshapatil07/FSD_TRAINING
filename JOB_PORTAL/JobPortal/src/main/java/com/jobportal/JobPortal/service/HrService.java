package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.HrRepository;

import jakarta.transaction.Transactional;

import com.jobportal.JobPortal.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**Service class to handle business logicc related to HR entities.*/

@Service
public class HrService {

    @Autowired
    private HrRepository hrRepository;

    @Autowired
    private UserService userService;

    public HrService(HrRepository hrRepository, UserService userService) {
        super();
        this.hrRepository = hrRepository;
        this.userService = userService;
    }

    //------------------- Registers a new HR under an existing user having ROLE as HR -----------------------

    public Hr registerHr(Hr hr) {
        // Extract the user from the HR object
        User user = hr.getUser();

        // Set the role to HR for this user
        user.setRole("HR");

        // Sign up the user 
        user = userService.signUp(user);

        // Attach the saved user back to HR entity
        hr.setUser(user);

        // Save the HR entity in the repository
        return hrRepository.save(hr);
    }

    //--------------------------- Get HR by ID ----------------------------------------------------------------------------------
    public Hr getHrById(int hrId) {
        return hrRepository.findById(hrId)
                .orElseThrow(() -> new ResourceNotFoundException("HR not found with ID: " + hrId));
    }

    //-------------------------- Updates an existing HR's name and company ----------------------------------------------------
    public Hr updateHr(int hrId, Hr updatedHr) {
        Hr existingHr = hrRepository.findById(hrId)
                .orElseThrow(() -> new ResourceNotFoundException("HR not found with ID: " + hrId));

        existingHr.setName(updatedHr.getName());
        existingHr.setCompanyName(updatedHr.getCompanyName());

        return hrRepository.save(existingHr);
    }

    //------------- Deletes an HR by ID ---------------------------------------------------------------------------------
    @Transactional
    public void deleteHr(int hrId) {
        if (!hrRepository.existsById(hrId)) {
            throw new ResourceNotFoundException("HR not found with ID: " + hrId);
        }
        hrRepository.deleteById(hrId);
    }

    //------------- Get all HRs------------------
    public List<Hr> getAllHrs() {
        return hrRepository.findAll();
    }
}
