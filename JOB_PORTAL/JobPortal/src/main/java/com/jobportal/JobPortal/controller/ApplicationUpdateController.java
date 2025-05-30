package com.jobportal.JobPortal.controller;

import com.jobportal.JobPortal.model.ApplicationUpdate;
import com.jobportal.JobPortal.service.ApplicationUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/updates")
public class ApplicationUpdateController {

    @Autowired
    private ApplicationUpdateService updateService;

    @PostMapping
    public ApplicationUpdate createUpdate(@RequestBody ApplicationUpdate update) {
        return updateService.saveUpdate(update);
    }

    @GetMapping
    public List<ApplicationUpdate> getAllUpdates() {
        return updateService.getAllUpdates();
    }

    @GetMapping("/{id}")
    public ApplicationUpdate getUpdateById(@PathVariable Long id) {
        return updateService.getUpdateById(id).orElse(null);
    }

    @GetMapping("/application/{applicationId}")
    public List<ApplicationUpdate> getByApplicationId(@PathVariable Long applicationId) {
        return updateService.getUpdatesByApplicationId(applicationId);
    }

    @DeleteMapping("/{id}")
    public void deleteUpdate(@PathVariable Long id) {
        updateService.deleteUpdate(id);
    }
}
