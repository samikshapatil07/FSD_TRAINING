package com.example.ReviewModel.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ReviewModel.dto.ReviewDTO;
import com.example.ReviewModel.model.Review;
import com.example.ReviewModel.service.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
	
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewService reviewService;
    
	public ReviewController(ReviewService reviewService) {
		super();
		this.reviewService = reviewService;
		}

//------------- add review if customer has purchased the product------------

@PostMapping("/add")
public ResponseEntity<ReviewDTO> addReview( @RequestParam ReviewDTO dto,
                                  Principal principal) {

	ReviewDTO savedReview = reviewService.addReview(dto, principal);
    return ResponseEntity.ok(savedReview);
}

//----------------Get all reviews for a given product---------------------------
@GetMapping("/product/{productId}")
public ResponseEntity<List<ReviewDTO>> getReviewsByProduct(@PathVariable int productId) {
    List<ReviewDTO> reviews = reviewService.getReviewsByProduct(productId);
    return ResponseEntity.ok(reviews);
}
}

