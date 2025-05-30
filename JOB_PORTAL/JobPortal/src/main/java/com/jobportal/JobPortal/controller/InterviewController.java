package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @PostMapping
    public Interview createInterview(@RequestBody Interview interview) {
        return interviewService.saveInterview(interview);
    }

    @GetMapping
    public List<Interview> getAllInterviews() {
        return interviewService.getAllInterviews();
    }

    @GetMapping("/{id}")
    public Interview getInterviewById(@PathVariable Long id) {
        return interviewService.getInterviewById(id).orElse(null);
    }

    @GetMapping("/application/{applicationId}")
    public List<Interview> getByApplicationId(@PathVariable Long applicationId) {
        return interviewService.getInterviewsByApplicationId(applicationId);
    }

    @DeleteMapping("/{id}")
    public void deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterview(id);
    }
}
