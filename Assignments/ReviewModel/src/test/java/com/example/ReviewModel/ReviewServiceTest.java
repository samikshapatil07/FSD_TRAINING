package com.example.ReviewModel;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ReviewModel.model.Review;
import com.example.ReviewModel.repository.ReviewRepository;
import com.example.ReviewModel.service.ReviewService;


@SpringBootTest
public class ReviewServiceTest {
	
	 @InjectMocks
	    private ReviewService reviewServiceTest;
	    @Mock
	    private ReviewRepository reviewRepository;

	   
	    private Review review;

 //write all test cases here................. to be implemented yet
}
