package com.jobportal.JobPortal.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.JobPortal.model.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {
	
	@Query
	("SELECT i FROM Interview i WHERE i.application.applicationId = ?1")
	List<Interview> findByApplicationApplicationId( Integer applicationId);
	
	@Query("SELECT i FROM Interview i WHERE i.application.jobPosting.hr.user.username = ?1")
	List<Interview> findAllByHrUsername( String username);
	
	@Query("SELECT i FROM Interview i WHERE i.application.jobSeeker.user.username = ?1")
	List<Interview> findAllByJsUsername( String username);
	
	
}
