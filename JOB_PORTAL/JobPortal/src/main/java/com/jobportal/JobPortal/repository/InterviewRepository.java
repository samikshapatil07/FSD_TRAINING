package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InterviewRepository extends JpaRepository<Interview, Long> {
    List<Interview> findByApplication_ApplicationId(Long applicationId);
}
