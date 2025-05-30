package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.model.ApplicationUpdate;
import com.jobportal.JobPortal.repository.ApplicationUpdateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationUpdateService {

    @Autowired
    private ApplicationUpdateRepository updateRepository;

    public ApplicationUpdate saveUpdate(ApplicationUpdate update) {
        return updateRepository.save(update);
    }

    public List<ApplicationUpdate> getAllUpdates() {
        return updateRepository.findAll();
    }

    public Optional<ApplicationUpdate> getUpdateById(Long id) {
        return updateRepository.findById(id);
    }

    public List<ApplicationUpdate> getUpdatesByApplicationId(Long applicationId) {
        return updateRepository.findByApplication_ApplicationId(applicationId);
    }

    public void deleteUpdate(Long id) {
        updateRepository.deleteById(id);
    }
}
