package com.springboot.lms.service;

import org.springframework.stereotype.Service;

import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.CModule;
import com.springboot.lms.model.Course;
import com.springboot.lms.repository.CourseRepository;
import com.springboot.lms.repository.ModuleRepository;

@Service
public class ModuleService {

	private CourseRepository courseRepository;
	private ModuleRepository moduleRepository;
	
	public ModuleService(CourseRepository courseRepository, ModuleRepository moduleRepository) {
		this.courseRepository = courseRepository;
		this.moduleRepository = moduleRepository;
	}


	public CModule addModule(int courseId, CModule module) {
		 // Fetch Course from courseId 
		 Course course = courseRepository.findById(courseId)
				 	.orElseThrow(()-> new ResourceNotFoundException("Course Not Found, Id Given is Invalid!"));
		
		 // Attach this course to the module object 
		 module.setCourse(course);
		 
		 //Save module in the DB
		return moduleRepository.save(module);
	}

}