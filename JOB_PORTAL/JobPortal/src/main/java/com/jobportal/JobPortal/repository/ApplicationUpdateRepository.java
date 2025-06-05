package com.jobportal.JobPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.JobPortal.model.ApplicationUpdate;

@Repository
public interface ApplicationUpdateRepository extends JpaRepository<ApplicationUpdate, Integer> {
	
	//implementing jpql
	@Query
	("select au from ApplicationUpdate au where au.applicationUpdate.applicationId =?1")
	List<ApplicationUpdate> findByApplicationApplicationId(Integer applicationId);
	
	@Query
	("select au from ApplicationUpdate au where au.applicationUpdate.jobSeekerId =?1 ")
	List<ApplicationUpdate> findByJobSeekerJobSeekerId(Integer jobSeekerId);

    //List<ApplicationUpdate> findByApplicationApplicationId(Integer applicationId);

    //List<ApplicationUpdate> findByJobSeekerJobSeekerId(Integer jobSeekerId);
}
