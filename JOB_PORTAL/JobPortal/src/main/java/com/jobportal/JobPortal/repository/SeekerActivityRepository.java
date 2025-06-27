package com.jobportal.JobPortal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.JobPortal.model.SeekerActivity;

@Repository
public interface SeekerActivityRepository extends JpaRepository<SeekerActivity, Integer> {
	
	@Query
	("select sa from SeekerActivity sa where sa.jobSeeker.jobSeekerId = ?1")
    List<SeekerActivity> findByJobSeekerById(int jobSeekerId);
}
