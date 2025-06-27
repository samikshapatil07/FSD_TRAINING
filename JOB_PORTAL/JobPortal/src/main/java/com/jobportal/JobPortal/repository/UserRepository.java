package com.jobportal.JobPortal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jobportal.JobPortal.model.User;

//this interface handles all DB operations related to the user entity
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//query to get user by usrname
	@Query("select u from User u where u.username=?1")
	User getByUsername(String username);
	
	User findByUsername(String username);
			

}
