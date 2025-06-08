package com.example.ReviewModel.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ReviewModel.dto.ReviewDTO;
import com.example.ReviewModel.model.Customer;
import com.example.ReviewModel.model.CustomerProduct;
import com.example.ReviewModel.model.Product;
import com.example.ReviewModel.model.Review;
import com.example.ReviewModel.model.User;
import com.example.ReviewModel.repository.CustomerProductRepository;
import com.example.ReviewModel.repository.CustomerRepository;
import com.example.ReviewModel.repository.ProductRepository;
import com.example.ReviewModel.repository.ReviewRepository;
import com.example.ReviewModel.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerProductRepository customerProductRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    
    //constructor
	public ReviewService(ReviewRepository reviewRepository, CustomerProductRepository customerProductRepository,
			CustomerRepository customerRepository, ProductRepository productRepository, UserRepository userRepository) {
		super();
		this.reviewRepository = reviewRepository;
		this.customerProductRepository = customerProductRepository;
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}
	
//------------------------add review if product is purchased-------------------------------
	public ReviewDTO addReview(ReviewDTO dto, Principal principal) {
        // Step 1: Get logged-in User
        String username = principal.getName();
        
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Step 2: Get Customer by User
        Customer customer = customerRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        // Step 3: Get Product by ID
        Product product = productRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Step 4: Check if Customer has purchased the Product
        boolean  purchase = customerProductRepository.existsByCustomerAndProduct(customer, product);
        if (!purchase) {
            throw new IllegalStateException("Customer has not purchased this product");
        }

        // Step 5: Save the Review
        Review review = new Review();
        review.setComment(dto.getComment());
        review.setRating(dto.getRating());
        review.setCustomer(customer);
        review.setProduct(product);
        Review saved = reviewRepository.save(review);

        return new ReviewDTO(
                saved.getId(),
                saved.getComment(),
                saved.getRating(),
                customer.getName(),
                product.getTitle()
        );    }
	
//------------------get review--------------------------------------------

	public List<ReviewDTO> getReviewsByProduct(int productId) {
		Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return reviewRepository.getReviewsByProductId(productId);	
	}

}