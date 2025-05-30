package com.jobportal.JobPortal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jobportal.JobPortal.model.JobPosting;


public interface JobPostingRepository extends JpaRepository<JobPosting, Long> {
	@Query("SELECT j FROM JobPosting j " +
		       "WHERE (:jobTitle IS NULL OR j.jobTitle LIKE %:jobTitle%) " +
		       "AND (:location IS NULL OR j.location LIKE %:location%) " +
		       "AND (:company IS NULL OR j.company LIKE %:company%)")
		List<JobPosting> searchJobs(@Param("jobTitle") String jobTitle,
		                            @Param("location") String location,
		                            @Param("company") String company);
}
