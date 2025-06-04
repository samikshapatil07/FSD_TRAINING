package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Application.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    // get applications by JobSeeker ID
    List<Application> findByJobSeeker_JobSeekerId(int jobSeekerId);

    // get applications by JobPosting ID
    List<Application> findByJobPosting_JobId(int jobId);

    // get applications by status
    List<Application> findByStatus(Status status);

}
