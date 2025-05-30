package com.jobportal.JobPortal.service;

import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.repository.InterviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;

    public Interview saveInterview(Interview interview) {
        return interviewRepository.save(interview);
    }

    public List<Interview> getAllInterviews() {
        return interviewRepository.findAll();
    }

    public Optional<Interview> getInterviewById(Long id) {
        return interviewRepository.findById(id);
    }

    public List<Interview> getInterviewsByApplicationId(Long applicationId) {
        return interviewRepository.findByApplication_ApplicationId(applicationId);
    }

    public void deleteInterview(Long id) {
        interviewRepository.deleteById(id);
    }
}
