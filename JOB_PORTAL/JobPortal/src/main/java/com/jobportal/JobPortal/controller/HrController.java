package com.jobportal.JobPortal.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.JobPortal.dto.HrDTO;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.service.HrService;

@RestController
@RequestMapping("/api/hr")
public class HrController {

    @Autowired
    private HrService hrService;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("HrController");
    
   //implementing dto here and then using it in every method
    private HrDTO convertToDTO(Hr hr) {
        HrDTO dto = new HrDTO();
        dto.setId(hr.getId());
        dto.setName(hr.getName());
        dto.setCompanyName(hr.getCompanyName());
        dto.setUsername(hr.getUser() != null ? hr.getUser().getUsername() : null);
        return dto;
    }

    private List<HrDTO> convertToDTOList(List<Hr> hrList) {
        List<HrDTO> dtoList = new ArrayList<>();
        for (Hr hr : hrList) {
            dtoList.add(convertToDTO(hr));
        }
        return dtoList;
    }

    // ------------------------- Register HR ---------------------------------------------
    /*
     * AIM     : To register a new HR having user role as HR
     * PATH    : /api/hr/register
     * METHOD  : POST
     * INPUT   : Hr (request body)
     * RESPONSE: Hr (saved HR object)
     */
    @PostMapping("/register")
    public ResponseEntity<HrDTO> registerHr(@RequestBody Hr hr) {
    	//logger
        logger.info("Registering new HR...");
        Hr savedHr = hrService.registerHr(hr);
        //dto
        HrDTO dto = convertToDTO(savedHr);
        return ResponseEntity.ok(dto);
    }
    // ------------------------- Get HR by ID ---------------------------------------------
    /*
     * AIM     : To get HR details by ID
     * PATH    : /api/hr/{hrId}
     * METHOD  : GET
     * INPUT   : hrId (path variable)
     * RESPONSE: Hr (HR object)
     */
    @GetMapping("/{hrId}")
    public ResponseEntity<HrDTO> getHrById(@PathVariable int hrId) {
    	//logger
        logger.info("Fetching HR by ID: " + hrId);
        Hr hr = hrService.getHrById(hrId);
        //dto
        HrDTO dto = convertToDTO(hr);
        return ResponseEntity.ok(dto);
    }
    
    // ------------------------- Get All HRs ---------------------------------------------
    /*
     * AIM     : To get a list of all HRs
     * PATH    : /api/hr
     * METHOD  : GET
     * RESPONSE: List<Hr> (all HR records)
     */
    @GetMapping
    public ResponseEntity<List<HrDTO>> getAllHrs() {
    	//logger
        logger.info("Fetching all HRs...");
        List<Hr> hrList = hrService.getAllHrs();
        //dto
        List<HrDTO> dtoList = convertToDTOList(hrList);
        return ResponseEntity.ok(dtoList);
    }

    // ------------------------- Update HR ---------------------------------------------
    /*
     * AIM     : To update HR details
     * PATH    : /api/hr/{hrId}
     * METHOD  : PUT
     * INPUT   : hrId (path variable), Hr (request body)
     * RESPONSE: String (confirmation message)
     */
    @PutMapping("/{hrId}")
    public ResponseEntity<String> updateHr(@PathVariable int hrId, @RequestBody Hr updatedHr) {
        //logger
    	logger.info("Updating HR with ID: " + hrId);
        hrService.updateHr(hrId, updatedHr);
        return ResponseEntity.ok("HR with ID " + hrId + " updated successfully.");
    }
    // ------------------------- Delete HR ---------------------------------------------
    /*
     * AIM     : To delete an HR by ID
     * PATH    : /api/hr/{hrId}
     * METHOD  : DELETE
     * INPUT   : hrId (path variable)
     * RESPONSE: String (confirmation message)
     */
    @DeleteMapping("/{hrId}")
    public ResponseEntity<?> deleteHr(@PathVariable int hrId) {
        hrService.deleteHr(hrId);
        //logger
        logger.info("Deleted HR with ID : " + hrId);
        return ResponseEntity.ok("HR with ID " + hrId + " has been deleted successfully.");
    }

}
