package com.jobportal.JobPortal.repository;

import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HrRepository extends JpaRepository<Hr, Integer> {
	
    @Query("select h from Hr h where h.user.username=?1")
	Hr getByUsername(String username);
    
    Optional<Hr> findByUser(User user);

	Optional<Hr> findById(int Id);

	boolean existsById(int Id);

	void deleteById(int Id);
}
