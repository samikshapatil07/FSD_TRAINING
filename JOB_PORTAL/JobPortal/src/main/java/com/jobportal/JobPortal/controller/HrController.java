package com.jobportal.JobPortal.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.JobPortal.dto.HrDTO;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.service.HrService;

//*this class handles the 3 API call : 1.register HR
//*                                    2. get logged in HR
//*                                    3. update HR profile (else are for executive that i have not implemented)*/

@RestController
@CrossOrigin(origins = "http://localhost:5173") //allows requests from the frontend running on port 5173
@RequestMapping("/api/hr")
public class HrController {

    @Autowired
    private HrService hrService;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("HrController");

    // ------------------------- Register HR ---------------------------------------------
    /*
     * AIM     : To register a new HR having user role as HR
     * PATH    : /api/hr/register
     * METHOD  : POST
     * INPUT   : Hr (request body)
     * RESPONSE: Hr (saved HR object)
     */
    @PostMapping("/register")
    public Hr registerHr(@RequestBody Hr hr) {
    	//logger
        logger.info("Registering new HR...");
        return hrService.registerHr(hr); //calling service ,method to register and return result
    }

    
    // ------------------------- Update HR ---------------------------------------------
    /*
     * AIM     : To update HR details
     * PATH    : /api/hr/{hrId}
     * METHOD  : PUT
     * INPUT   : hrId (path variable), Hr (request body)
     * RESPONSE: String (confirmation message)
     */    
    @PostMapping("/update/me")
    public ResponseEntity<Hr> updateMyHrProfile(@RequestBody Hr updatedHr, Principal principal) {
        logger.info("Updating profile of logged-in HR...");

        //fetch logged in hr details using username from principle obj.
        HrDTO existing = hrService.getHrByUsername(principal.getName()); 
        //update the hr data using the service methid
       Hr updated =  hrService.updateHr(existing.getId(), updatedHr);
       //retirn updated hr
        return ResponseEntity.ok(updated);
    }
    
    // ------------------------- login as hr --------------------------------------------
    /*
     * AIM     : To get HR by username
     * PATH    : /api/hr/{username}
     * METHOD  : GET
     * INPUT   : @path variable hrusername
     * RESPONSE: hr
     */
    @GetMapping("/username/{username}")
    public HrDTO getHrByUsername(@PathVariable String username) {
    	//logger
        logger.info("Fetching HR with username: " + username);
        
        return hrService.getHrByUsername(username); //call service methods and return dto
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
 //======================================================================================================
    //-------------------------------- for EX -----------------------------------------------------------------------
    
    // ------------------------- Delete HR ---------------------------------------------
    /*
     * AIM     : To delete an HR by ID
     * PATH    : /api/hr/{hrId}
     * METHOD  : DELETE
     * INPUT   : hrId (path variable)
     * RESPONSE: String (confirmation message)
     */
    @DeleteMapping("/delete/{hrId}")
    public ResponseEntity<?> deleteHr(@PathVariable int hrId) {
        hrService.deleteHr(hrId);
        //logger
        logger.info("Deleted HR with ID : " + hrId);
        return ResponseEntity.ok("HR with ID " + hrId + " has been deleted successfully.");
    }
    
    // ------------------------- Get All HRs ---------------------------------------------
    /*
     * AIM     : To get a list of all HRs
     * PATH    : /api/hr
     * METHOD  : GET
     * RESPONSE: List<Hr> (all HR records)
     */
    @GetMapping("/all")
    public List<HrDTO> getAllHrs() {
    	//logger
        logger.info("Fetching all HRs...");
        return hrService.getAllHrs();
    }


    
    // ------------------------- Get HR by ID ---------------------------------------------
    /*
     * AIM     : To get HR details by ID
     * PATH    : /api/hr/{hrId}
     * METHOD  : GET
     * INPUT   : hrId (path variable)
     * RESPONSE: Hr 
     */
    @GetMapping("/Id/{hrId}")
    public HrDTO getHrById(@PathVariable int hrId) {
    	//logger
        logger.info("Fetching HR by ID: " + hrId);
        return hrService.getHrById(hrId);
    }



}
