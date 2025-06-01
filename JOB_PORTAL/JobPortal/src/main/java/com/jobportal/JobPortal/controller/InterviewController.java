package com.jobportal.JobPortal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.service.InterviewService;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService service;

    // ------------------ Schedule a new Interview -------------------
    /*
     * AIM     : To schedule a new interview for an application
     * PATH    : /api/interviews
     * METHOD  : POST
     * INPUT   : Interview object in request body
     * RESPONSE: Interview (saved interview with details)
     */
    @PostMapping
    public ResponseEntity<Interview> schedule(@RequestBody Interview interview) {
        return new ResponseEntity<>(service.scheduleInterview(interview), HttpStatus.CREATED);
    }

    // -------------- Get  Interviews by Application ID -------------
    /*
     * AIM     : To get all interviews associated with a specific application
     * PATH    : /api/interviews/application/{appId}
     * METHOD  : GET
     * INPUT   : appId (application ID path variable)
     * RESPONSE: List<Interview> (list of interviews)
     */
    @GetMapping("/application/{appId}")
    public ResponseEntity<List<Interview>> getByAppId(@PathVariable Integer appId) {
        return ResponseEntity.ok(service.getInterviewsByApplicationId(appId));
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
    public ResponseEntity<Interview> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getInterviewById(id));
    }

    // ------------------ Update Interview Details (HR)----------------------
    /*
     * AIM     : To update interview details
     * PATH    : /api/interviews
     * METHOD  : PUT
     * INPUT   : Interview object with updated details in request body
     * RESPONSE: Updated Interview object
     */
    @PutMapping
    public ResponseEntity<Interview> update(@RequestBody Interview interview) {
        return ResponseEntity.ok(service.updateInterview(interview));
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
    public ResponseEntity<Interview> updateOutcome(@PathVariable Integer id,
                                                   @RequestParam("status") String status) {
        Interview.InterviewOutcome outcome;
        try {
            outcome = Interview.InterviewOutcome.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }

        Interview updated = service.updateInterviewStatus(id, outcome);
        return ResponseEntity.ok(updated);
    }
}
