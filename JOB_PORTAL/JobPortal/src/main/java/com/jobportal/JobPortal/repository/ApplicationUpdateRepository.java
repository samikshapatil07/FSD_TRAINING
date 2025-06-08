package com.jobportal.JobPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.JobPortal.model.ApplicationUpdate;

@Repository
public interface ApplicationUpdateRepository extends JpaRepository<ApplicationUpdate, Integer> {
	
	//implementing jpql
	// Correct JPQL: Get updates by application ID
    @Query("SELECT au FROM ApplicationUpdate au WHERE au.application.applicationId = ?1")
    List<ApplicationUpdate> findByApplication_ApplicationId(Integer applicationId);

    // Correct JPQL: Get updates by job seeker ID
    @Query("SELECT au FROM ApplicationUpdate au WHERE au.jobSeeker.jobSeekerId = ?1")
    List<ApplicationUpdate> findByJobSeeker_JobSeekerId(Integer jobSeekerId);
}

//List<ApplicationUpdate> findByApplication_ApplicationId(Integer applicationId);
//List<ApplicationUpdate> findByJobSeeker_JobSeekerId(Integer jobSeekerId);
