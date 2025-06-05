package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.SeekerActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeekerActivityRepository extends JpaRepository<SeekerActivity, Integer> {
	
	@Query
	("select sa from SeekerActivity sa where  sa.seekerActivity.jobSeekerId =?1")
    List<SeekerActivity> findByJobSeeker_JobSeekerId(int jobSeekerId);
}
