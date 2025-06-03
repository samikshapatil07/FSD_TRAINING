package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Course;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.LearnerCourse;
import com.springboot.lms.service.LearnerCourseService;

@RestController
public class LearnerCourseController {

	@Autowired
	private LearnerCourseService learnerCourseService;

	/*
	 * AIM: Enroll learner to course by adding record in relation entity
	 * PATH: /api/learner/enroll/course/{learnerId}/{courseId}
	 * METHOD: POST
	 * PARAM: Path variable(learnerId,courseId) , Request Body ({ couponCode: ""})
	 * Response: LearnerCourse
	 * 
	 */
	@PostMapping("/api/learner/enroll/course/{learnerId}/{courseId}")
	public LearnerCourse enrollLearnerInCourse(@PathVariable int learnerId,
			@PathVariable int courseId,
			@RequestBody LearnerCourse learnerCourse) {
		return learnerCourseService.enrollLearnerInCourse(learnerId, courseId, learnerCourse);
	}

	/*
	 * AIM: Fetch all learners that have enrolled in given course
	 * PATH: /api/learner/enroll/course/{courseId}
	 * METHOD: GET
	 * PARAM: Path variable(courseId)
	 * Response: List<Learner>
	 */
	@GetMapping("/api/learner/enroll/course/{courseId}")
	public List<Learner> getLearnerByCourseId(@PathVariable int courseId) {
		return learnerCourseService.getLearnerByCourseId(courseId);
	}

	@GetMapping("/api/course/learner/{learnerId}")
	public List<Course> getCoursesByLearnerId(@PathVariable int learnerId) {
		return learnerCourseService.getCoursesByLearnerId(learnerId);
	}
}