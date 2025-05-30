package com.springboot.lms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.lms.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

	//@Query(value = "select * from review where rating > ?1",  nativeQuery = true)
	@Query("select r from Review r where r.rating > ?1")
	List<Review> getByRating(String rating);
	//List<Review> findBySummary(String summary);
}