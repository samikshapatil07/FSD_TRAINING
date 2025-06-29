package com.jobportal.JobPortal.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.JobPortal.dto.InterviewDTO;
import com.jobportal.JobPortal.dto.InterviewOutcomeDTO;
import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.model.Interview.InterviewOutcome;
import com.jobportal.JobPortal.service.InterviewService;

@RestController
@CrossOrigin(origins = "http://localhost:5173") // allowing CORS from frontend running at this port

@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("InterviewController");

 // ------------------ Schedule a new Interview -------------------
    /* FE: HR------->ScheduleInterview
     * AIM     : To schedule a new interview for an application
     * PATH    : /api/interviews
     * METHOD  : POST
     * INPUT   : Interview object in request body app id as path variable, principal object to get logges in user
     * RESPONSE: Interview (saved interview with details)
     */   
    
    @PostMapping("/schedule/application/{appId}")
    public ResponseEntity<InterviewDTO> schedule(@PathVariable int appId, @RequestBody Interview interview,
    		Principal principal) {
        String username = principal.getName();
        Interview scheduled = interviewService.scheduleInterview(appId, interview, username);

        InterviewDTO dto = InterviewDTO.converttoDto(scheduled);
        logger.info("Scheduled Interview for Application ID: " + appId + " by HR: " + username);
        return ResponseEntity.ok(dto);
    }
   

// ------------------ Update Interview Outcome Status ---------------
    /* FE: HR-------> Interviews
     * AIM     : To update the outcome/status of an interview
     * PATH    : /api/interviews/{id}/status?status=OUTCOME
     * METHOD  : PATCH
     * INPUT   : id (interview ID path variable), status (query param)
     * RESPONSE: Updated Interview object
     */
    @PostMapping("/update/outcome/interviewId/{interviewId}")
    public InterviewDTO updateInterviewOutcome(
            @PathVariable int interviewId, 
            @RequestBody InterviewOutcomeDTO outcomeDTO) {

        logger.info("HR Updating Interview Outcome for " + interviewId);
        InterviewOutcome outcome = InterviewOutcome.valueOf(outcomeDTO.getOutcome().toUpperCase());

        return interviewService.updateInterviewOutcome( interviewId,  outcome);
    }
    
 // ------------------ Get  Interviews by hr-----------------
  //  FE: HR-------> Interviews
  @GetMapping("/by-hr")
  public List<InterviewDTO> getInterviewsByHR(Principal principal) {
      String username = principal.getName(); //logged inn hr
      return interviewService.getInterviewsByHRUsername(username);
  }
  
  
   
  // ------------------ Get  Interviews for js-----------------
   //FE: HR-------> Interviews
  @GetMapping("/for-js")
  public List<InterviewDTO> getInterviewsForJS(Principal principal) {
      String username = principal.getName(); // logged in js
      return interviewService.getInterviewsForJS(username);
  }
    


  
  
  
  
  
  
  
  
  
  
  
  
  //============================================================================not executed =======================================

// ------------------ Delete an Interview ---------------------------
    /*
     * AIM     : To delete an interview by ID
     * PATH    : /api/interviews/{id}
     * METHOD  : DELETE
     * INPUT   : id (interview ID path variable)
     */
    @DeleteMapping("/delete/{interviewId}")
    public ResponseEntity<String> deleteInterview(@PathVariable int interviewId) {
        logger.info("Deleting Interview with id: " + interviewId);
        interviewService.deleteInterview(interviewId);
        return ResponseEntity.status(HttpStatus.OK).body("Interview deleted successfully..");
    }
     

    

    
    // ------------------ Update Interview Details (HR)----------------------
    /*  FE: HR------->ScheduleInterview
     * AIM     : To update interview details
     * PATH    : /api/interviews
     * METHOD  : PUT
     * INPUT   : Interview object with updated details in request body
     * RESPONSE: Updated Interview object
     */
    @PutMapping("/update-for/interview/{interviewId}")
    public Interview updateInterview(@PathVariable int interviewId,@RequestBody Interview interview) {
        //logger                      
        logger.info("Updating Interview ID: " + interview.getInterviewId() + " for application ID: " + interviewId);
        return interviewService.updateInterview(interview, interviewId);
    }

    
    
  
    
    
    
// ------------------ Get all Interviews -----------------

//    @GetMapping("/get-all")
//    public List<InterviewDTO> getAllInterviews(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//			@RequestParam(name = "size", required = false, defaultValue = "1000000") Integer size) {
//
//    	logger.info("Fetching applications for HR");
//        return interviewService.getAllInterviews(page, size);
//    }
    
 // ------------------ Get Interview by Interview ID -----------------
    /*
     * AIM     : To retrieve a specific interview by its ID
     * PATH    : /api/interviews/{id}
     * METHOD  : GET
     * INPUT   : id (interview ID path variable)
     * RESPONSE: Interview object
     */
    @GetMapping("/interviewId/{interviewId}")
    public Optional<Interview> getInterviewById(@PathVariable int interviewId) {
    	//logger
        logger.info("Interview with Interview id: " + interviewId);
        return interviewService.getInterviewById(interviewId);
    }
    
 // -------------- Get Interviews by Application ID -------------
    /*
     * AIM     : To get all interviews associated with a specific application
     * PATH    : /api/interviews/application/{appId}
     * METHOD  : GET
     * INPUT   : appId (application ID path variable)
     * RESPONSE: List<Interview> (list of interviews)
     */
    @GetMapping("/applicationId/{appId}")
    public List<InterviewDTO> getInterviewsByApplicationId(@PathVariable int appId) {
        logger.info("Interviews with application id: " + appId);
        return interviewService.getInterviewsByApplicationId(appId);
    }
   
   
}
