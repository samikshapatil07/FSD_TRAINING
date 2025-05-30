package com.jobportal.JobPortal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jobportal.JobPortal.model.JobPosting;


public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
    List<JobPosting> findByLocationContainingIgnoreCase(String location);
    List<JobPosting> findBySkillsContainingIgnoreCase(String skills);
    List<JobPosting> findByCompanyContainingIgnoreCase(String company);
}
