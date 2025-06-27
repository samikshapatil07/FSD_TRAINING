package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.Hr;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface HrRepository extends JpaRepository<Hr, Integer> {
	
    @Query("select h from Hr h where h.user.username=?1")
    Hr getHrByUsername(String username);    

}
