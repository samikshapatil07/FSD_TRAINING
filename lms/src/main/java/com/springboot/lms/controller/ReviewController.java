package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Review;
import com.springboot.lms.service.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	/*
	 * AIM: Create a POST API to insert review given by learner for a course 
	 * Method: POST
	 * PARAM: learnerId, courseId <- Path variable,|| comment,rating <- request body 
	 * Response: Review 
	 * API: /api/review/add/{learnerId}/{courseId}
	 * */
	@PostMapping("/add/{learnerId}/{courseId}")
	public Review postReview(@PathVariable int  learnerId, 
						   @PathVariable int  courseId, 
						   @RequestBody Review review) {
		
		return reviewService.postReview(learnerId,courseId, review);
	}
	
	/*
	 * AIM: Fetch all reviews where rating is more than given rating value
	 * Method: GET
	 * PARAM: rating (request parameter)
	 * Response: List<Review>
	 * API: /api/review/rating
	 * */
	@GetMapping("/rating")
	public List<Review> getReviewByRating(@RequestParam String rating) {
		return reviewService.getReviewByRating(rating);
		
	}
}