package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.JobSeeker;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Integer> {

	
    @Query("select js from JobSeeker js where js.user.username=?1")
    JobSeeker getJobSeekerByUsername(String username);

	Optional<JobSeeker> findByUserUsername(String username);
    
    
    
}
