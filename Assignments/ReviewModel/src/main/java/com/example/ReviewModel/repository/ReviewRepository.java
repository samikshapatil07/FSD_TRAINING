package com.example.ReviewModel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReviewModel.dto.ReviewDTO;
import com.example.ReviewModel.model.Product;
import com.example.ReviewModel.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProduct(Product product);

	List<ReviewDTO> getReviewsByProductId(int productId);
}