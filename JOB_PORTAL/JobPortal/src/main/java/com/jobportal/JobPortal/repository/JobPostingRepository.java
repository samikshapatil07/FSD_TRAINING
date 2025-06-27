package com.jobportal.JobPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.JobPortal.model.JobPosting;


public interface JobPostingRepository extends JpaRepository<JobPosting, Integer> {

	@Query("select j from JobPosting j where j.jobTitle = ?1 AND j.location = ?2 and j.company = ?3 ")  
		List<JobPosting> searchJobs(String jobTitle, String location, String company);

	@Query
	("select jp from JobPosting jp where jp.hr.user.username=?1")
	List<JobPosting> getJobsByHr(String username);
	
	@Query("select j from JobPosting j where j.jobId = :jobId")
	JobPosting getJobById(@Param("jobId") int jobId);

	}
