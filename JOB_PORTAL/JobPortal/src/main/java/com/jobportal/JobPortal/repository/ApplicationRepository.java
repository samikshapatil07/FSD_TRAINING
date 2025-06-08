package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Application.Status;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	
    // Get applications by JobSeeker ID
    @Query("SELECT a FROM Application a WHERE a.jobSeeker.jobSeekerId = ?1")
    List<Application> findByJobSeeker_JobSeekerId(int jobSeekerId);

    // Get applications by JobPosting ID
    @Query("SELECT a FROM Application a WHERE a.jobPosting.jobId = ?1")
    List<Application> findByJobPosting_JobId(Integer jobId);

    // Get applications by Status
    @Query("SELECT a FROM Application a WHERE a.status = ?1")
    List<Application> findByStatus(Status status);
}
	
	
    // get applications by JobSeeker ID
    //List<Application> findByJobSeeker_JobSeekerId(int jobSeekerId);

    // get applications by JobPosting ID
    //List<Application> findByJobPosting_JobId(int jobId);

    // get applications by status
    //List<Application> findByStatus(Status status);


