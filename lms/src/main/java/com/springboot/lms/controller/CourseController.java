
package com.springboot.lms.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 */
	@PostMapping("/add") // <-- /api/course/add
	public Course postCourse(Principal principal, @RequestBody Course course) {
		String username = principal.getName(); // LoggedIn author
		return courseService.postCourse(course, username);
	}

	@GetMapping("/all")
	public List<Course> getAllCourses(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "1000000") Integer size) {

		return courseService.getAllCourses(page, size);
	}

}
