package com.springboot.lms.service;

import org.springframework.stereotype.Service;

import com.springboot.lms.model.Course;
import com.springboot.lms.repository.CourseRepository;

@Service
public class CourseService {

	private CourseRepository courseRepository;
	
	public CourseService(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	public Course postCourse(Course course) {
		return courseRepository.save(course);
	}

}