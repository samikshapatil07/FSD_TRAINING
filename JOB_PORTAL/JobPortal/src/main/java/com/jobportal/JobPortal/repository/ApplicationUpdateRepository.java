package com.jobportal.JobPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.JobPortal.model.ApplicationUpdate;

@Repository
public interface ApplicationUpdateRepository extends JpaRepository<ApplicationUpdate, Integer> {
	
	//implementing jpql
	//Get updates by application ID
    @Query("select au from ApplicationUpdate au where au.application.applicationId = ?1")
    List<ApplicationUpdate> get_updates_by_app(int applicationId);

    // Get updates by job seeker ID(for ex)
    @Query("select  au from ApplicationUpdate au where au.jobSeeker.jobSeekerId = ?1")
    List<ApplicationUpdate> get_updates_by_js(int jobSeekerId);
}
