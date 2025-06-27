package com.jobportal.JobPortal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.dto.HrDTO;
import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.HrRepository;

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
    	//extract the user obj. linked to HR
        User user = hr.getUser();
        //set the role to hr
        user.setRole("HR");
        //register the user by user service
        user = userService.signUp(user);
        //set the registered user back to HR
        hr.setUser(user);
        //sav the hr to DB
        return hrRepository.save(hr);
    }

    //-------------------------- Updates an existing HR's name and company ----------------------------------------------------
    public Hr updateHr(int hrId, Hr updatedHr) {
    	//find the hr by id
        Hr existingHr = hrRepository.findById(hrId)
                .orElseThrow(() -> new ResourceNotFoundException("HR not found with ID: " + hrId));
		//if name is provided, update it
        if (updatedHr.getName() != null)
        existingHr.setName(updatedHr.getName());
     
        if (updatedHr.getCompanyName() != null)
        existingHr.setCompanyName(updatedHr.getCompanyName());

        //save the updated hr to db
        return hrRepository.save(existingHr);
    }
    
  //------------- Get HR by username ------------------
	public HrDTO getHrByUsername(String username) {	
		//find hr by username
		Hr hr = hrRepository.getHrByUsername(username);
		if (hr == null) {
            throw new ResourceNotFoundException("Job Seeker not found with username: " + username);
        }
		//convert hr entity to dto and return
		return HrDTO.converttoDto(hr);
    }
	
	
	
	
	
	
	
	
	
	
	
	//================================ for ex ========================================

    //------------- Deletes an HR by ID ---------------------------------------------------------------------------------
    public void deleteHr(int hrId) {
        if (!hrRepository.existsById(hrId)) {
            throw new ResourceNotFoundException("HR not found with ID: " + hrId);
        }
        hrRepository.deleteById(hrId);
    }

    //------------- Get all HRs------------------
    public List<HrDTO> getAllHrs() {
    	List<Hr> hr = hrRepository.findAll();
        return HrDTO.converttoDto(hr);
    }

    //--------------------------- Get HR by ID ----------------------------------------------------------------------------------
    public HrDTO getHrById(int hrId) {
    	Hr hr = hrRepository.findById(hrId)
                .orElseThrow(() -> new ResourceNotFoundException("HR not found with ID: " + hrId));
    	 return HrDTO.converttoDto(hr);
    }
}

