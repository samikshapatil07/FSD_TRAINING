package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Application.Status;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	
	//jpql to get applications by JobSeeker ID
	@Query
	("SELECT a FROM Application a where a.application.jobSeekerId =?1")
	List<Application> findByJobSeeker_JobSeekerId(int jobSeekerId);
	
	//jpql to get applications by job ID
	@Query
	("SELECT a FROM Appliation a WHERE a.application.jobId =?1")
	List<Application> findByJobPosting_JobId(int jobId);
	
	//jpql to get status
	@Query
	("select a from application a where a.application.ststus =?1")
	Application findByStatus(Status status);
	
	
    // get applications by JobSeeker ID
    //List<Application> findByJobSeeker_JobSeekerId(int jobSeekerId);

    // get applications by JobPosting ID
    //List<Application> findByJobPosting_JobId(int jobId);

    // get applications by status
    //List<Application> findByStatus(Status status);

}
