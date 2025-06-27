package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.dto.ApplicationDTO;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Application.Status;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

    // Get applications by JobPosting ID
    @Query("SELECT a FROM Application a WHERE a.jobPosting.jobId = ?1")
    List<ApplicationDTO> findByJobPosting_JobId(Integer jobId);

    // Get applications by Status
    @Query("SELECT a FROM Application a WHERE a.status = ?1")
    List<Application> findByStatus(Status status);
    

	
	@Query("SELECT a FROM Application a WHERE a.jobPosting.hr.user.username = ?1")
	List<ApplicationDTO> findApplicationsByHrUsername(String username);
	
	@Query("SELECT a FROM Application a WHERE a.jobPosting.hr.user.username = ?1")
	List<Application> getApplicationsForHr(String username);

	@Query("SELECT a FROM Application a WHERE a.jobSeeker.user.username = ?1")
	List<Application> getApplicationsForJs(String username);
	
	}
    





