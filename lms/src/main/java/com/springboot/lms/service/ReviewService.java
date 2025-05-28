
package com.springboot.lms.service;

import org.springframework.stereotype.Service;

import com.springboot.lms.exception.ResourceNotFoundException;
import com.springboot.lms.model.Course;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.LearnerCourse;
import com.springboot.lms.model.Review;
import com.springboot.lms.repository.CourseRepository;
import com.springboot.lms.repository.LearnerCourseRepository;
import com.springboot.lms.repository.LearnerRepository;
import com.springboot.lms.repository.ReviewRepository;

@Service
public class ReviewService {

	private ReviewRepository reviewRepository;
	private CourseRepository courseRepository;
	private LearnerRepository learnerRepository;
	private LearnerCourseRepository learnerCourseRepository;
	
	public ReviewService(ReviewRepository reviewRepository, CourseRepository courseRepository,
			LearnerRepository learnerRepository, LearnerCourseRepository learnerCourseRepository) {
		this.reviewRepository = reviewRepository;
		this.courseRepository = courseRepository;
		this.learnerRepository = learnerRepository;
		this.learnerCourseRepository = learnerCourseRepository;
	}

	public Review postReview(int learnerId, int courseId, Review review) {
		// Fetch LearnerCourse from learnerId and courseId using JPQL , Native SQL 
		
		//LearnerCourse learnerCourse =  learnerCourseRepository.getUsingNativeSql(learnerId,courseId)
		//			.orElseThrow(()->new ResourceNotFoundException("Learner not enrolled in course"));
		LearnerCourse learnerCourse =  learnerCourseRepository.getUsingJPQL(learnerId,courseId)
				.orElseThrow(()->new ResourceNotFoundException("Learner not enrolled in course"));
		
		// Attach LearnerCourse to review 
		review.setLearnerCourse(learnerCourse);
		
		//Add review in DB
		return reviewRepository.save(review);
	}
	
	

	
}
