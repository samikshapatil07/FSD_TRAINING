package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Learner;
import com.springboot.lms.model.LearnerCourse;
import com.springboot.lms.service.LearnerCourseService;

@RestController
public class LearnerCourseController {
	
	@Autowired
	private LearnerCourseService learnerCourseService;
	
//------------------- add learner to course-------------------------
		/*AIM: enroll learner to course by adding record to  relation entity
		 *PaTH: /api/learner/enroll/course/{learner id}/{course id}
		 *METHOD: POST 
		 * PARAM: Path variable(learnerId,courseId) , Request Body ({ couponCode: ""})
	     * Response: LearnerCourse */
		@PostMapping("/api/learner/enroll/course/{learnerId}/{courseId}")
		public LearnerCourse enrolllLearnerInCourse(@PathVariable int learnerId, 
				                           @PathVariable int courseId,
				                           @RequestBody LearnerCourse learnerCourse)
		{
			return  learnerCourseService.enrollLearnerInCourse(learnerId, courseId,learnerCourse); 
		}
		
		/*
		 * AIM: Fetch all learners that have enrolled in given course 
		 * PATH: /api/learner/enroll/course/{courseId}
		 * METHOD: GET 
		 * PARAM: Path variable(courseId)  
		 * Response: List<Learner>   
		 * */
		@GetMapping("/api/learner/enroll/course/{courseId}")
		public List<Learner> getLearnerByCourseId(@PathVariable int courseId) {
			return learnerCourseService.getLearnerByCourseId(courseId);
		}

}
