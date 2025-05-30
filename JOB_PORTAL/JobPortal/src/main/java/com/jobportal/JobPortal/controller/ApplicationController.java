package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return applicationService.saveApplication(application);
    }

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return applicationService.getApplicationById(id).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
    }

    @GetMapping("/jobseeker/{jobSeekerId}")
    public List<Application> getByJobSeekerId(@PathVariable Long jobSeekerId) {
        return applicationService.getApplicationsByJobSeekerId(jobSeekerId);
    }

    @GetMapping("/job/{jobId}")
    public List<Application> getByJobId(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJobId(jobId);
    }

    @PatchMapping("/{id}/status")
    public Application updateStatus(@PathVariable Long id, @RequestParam Application.Status status) {
        return applicationService.updateApplicationStatus(id, status);
    }

}
