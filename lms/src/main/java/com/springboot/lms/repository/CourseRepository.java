package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
