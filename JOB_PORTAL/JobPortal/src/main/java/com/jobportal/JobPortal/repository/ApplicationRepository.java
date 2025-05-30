package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByJobSeeker_JobSeekerId(Long jobSeekerId);
    List<Application> findByJobPosting_JobId(Long jobId);
}
