package com.jobportal.JobPortal.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobportal.JobPortal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("select u from User u where u.username=?1")
	User getByUsername(String username);
		
	Optional<User> getUserById(Long id);	
	
   // Checks if an username is already registered
    boolean existsByUsername(String username);

}
