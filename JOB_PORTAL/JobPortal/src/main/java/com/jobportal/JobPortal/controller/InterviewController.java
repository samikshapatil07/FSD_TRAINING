package com.jobportal.JobPortal.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.JobPortal.dto.InterviewDTO;
import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService service;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("InterviewController");
    
  // implementing dto <<<< Convert Interview entity to InterviewDTO>>>>
    private InterviewDTO convertToDTO(Interview interview) {
        InterviewDTO dto = new InterviewDTO();
        dto.setInterviewId(interview.getInterviewId());
        dto.setInterviewDate(interview.getInterviewDate().toString());
        dto.setInterviewLocation(interview.getInterviewLocation());
        dto.setInterviewMode(interview.getInterviewMode().toString());
        dto.setOutcome(interview.getOutcome().toString());
        dto.setApplicationId(interview.getApplication().getApplicationId());
        return dto;
    }

    // ------------------ Schedule a new Interview -------------------
    /*
     * AIM     : To schedule a new interview for an application
     * PATH    : /api/interviews
     * METHOD  : POST
     * INPUT   : Interview object in request body
     * RESPONSE: Interview (saved interview with details)
     */
    @PostMapping("/application/{appId}")
    public ResponseEntity<InterviewDTO> schedule(
            @PathVariable int appId,
            @RequestBody Interview interview) {
        Interview savedInterview = service.scheduleInterview(appId, interview);
        //dto
        InterviewDTO dto = convertToDTO(savedInterview);
        //logger
        logger.info("Scheduled Interview for" + appId + "with interview ID: " + interview);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // -------------- Get Interviews by Application ID -------------
    /*
     * AIM     : To get all interviews associated with a specific application
     * PATH    : /api/interviews/application/{appId}
     * METHOD  : GET
     * INPUT   : appId (application ID path variable)
     * RESPONSE: List<Interview> (list of interviews)
     */
    @GetMapping("/application/{appId}")
    public ResponseEntity<List<InterviewDTO>> getByAppId(@PathVariable Integer appId) {
        List<Interview> interviews = service.getInterviewsByApplicationId(appId);
        
        // Manually converting list of interviews to list of InterviewDTOs
        List<InterviewDTO> dtoList = new ArrayList<>();
        for (Interview interview : interviews) {
            InterviewDTO dto = convertToDTO(interview);
            dtoList.add(dto);
        }
        //logger
        logger.info("Interviews with application id: " + appId);
        return ResponseEntity.ok(dtoList);
    }
    // ------------------ Get Interview by Interview ID -----------------
    /*
     * AIM     : To retrieve a specific interview by its ID
     * PATH    : /api/interviews/{id}
     * METHOD  : GET
     * INPUT   : id (interview ID path variable)
     * RESPONSE: Interview object
     */
    @GetMapping("/{id}")
    public ResponseEntity<InterviewDTO> getById(@PathVariable Integer id) {
        Interview interview = service.getInterviewById(id);
        if (interview != null) {
            InterviewDTO dto = convertToDTO(interview);
            //logger
            logger.info("Interview with Interview id: " + id);
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    // ------------------ Update Interview Details (HR)----------------------
    /*
     * AIM     : To update interview details
     * PATH    : /api/interviews
     * METHOD  : PUT
     * INPUT   : Interview object with updated details in request body
     * RESPONSE: Updated Interview object
     */
    @PutMapping("/{applicationId}")
    public ResponseEntity<Interview> update(@PathVariable Integer applicationId,
                                            @RequestBody Interview interview) {
        logger.info("Updating Interview ID: " + interview.getInterviewId() + " for application ID: " + applicationId);
        return ResponseEntity.ok(service.updateInterview(interview, applicationId));
    }

    // ------------------ Delete an Interview ---------------------------
    /*
     * AIM     : To delete an interview by ID
     * PATH    : /api/interviews/{id}
     * METHOD  : DELETE
     * INPUT   : id (interview ID path variable)
     * RESPONSE: HTTP 204 No Content on success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Deleting Interview with id: " + id);
        service.deleteInterview(id);
        return ResponseEntity.noContent().build();
    }
    
    // ------------------ Update Interview Outcome Status ---------------
    /*
     * AIM     : To update the outcome/status of an interview
     * PATH    : /api/interviews/{id}/status?status=OUTCOME
     * METHOD  : PATCH
     * INPUT   : id (interview ID path variable), status (query param)
     * RESPONSE: Updated Interview object
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<InterviewDTO> updateOutcome(@PathVariable Integer id,
                                                      @RequestParam String status) {
        Interview.InterviewOutcome outcome;
        try {
            outcome = Interview.InterviewOutcome.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        Interview updated = service.updateInterviewStatus(id, outcome);
        //dto
        InterviewDTO dto = convertToDTO(updated);
        //logger
        logger.info("Updating Interview outcome for id: " + id);
        return ResponseEntity.ok(dto);
    }
}
