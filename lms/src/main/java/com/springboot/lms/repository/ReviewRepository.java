package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{

}