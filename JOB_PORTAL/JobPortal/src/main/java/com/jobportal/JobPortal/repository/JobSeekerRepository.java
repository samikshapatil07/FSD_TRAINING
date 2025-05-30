package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

	
    @Query("select js from Hr js where js.user.username=?1")
    JobSeeker getJobSeekerByUsername(String username);
    
    // Find JobSeeker by associated User ID
	Optional<JobSeeker> findByUser_Id(int id);  

    // Optional: Find by name, if needed for business logic
    Optional<JobSeeker> findByName(String name);

    Optional<JobSeeker> findByUser_Username(String username);
    
    
}
