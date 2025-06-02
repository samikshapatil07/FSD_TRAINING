package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.lms.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	@Query("select a from Author a where a.user.username=?1")
	Author getAuthorByUsername(String username);

}
