package com.springboot.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Course;
import com.springboot.lms.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

	@Autowired
	private CourseService courseService;
	/*
	 * AIM: Add course to DB <-- Should be done only by AUTHOR 
	 * PATH: /api/course/add
	 * METHOD: POST
	 * Response: Course 		
	 * Input: Course <-- request body
	 * Access: AUTHOR or EXECUTIVE 
	 * */
	@PostMapping("/add") //<-- /api/course/add 
	public Course postCourse(@RequestBody Course course) {
		return courseService.postCourse(course); 
	}
}