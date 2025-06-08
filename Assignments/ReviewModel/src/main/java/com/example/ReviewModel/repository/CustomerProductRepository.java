package com.example.ReviewModel.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReviewModel.model.Customer;
import com.example.ReviewModel.model.CustomerProduct;
import com.example.ReviewModel.model.Product;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Integer> {
	boolean existsByCustomerAndProduct(Customer customer, Product product); // ✅ Correct
    
	List<CustomerProduct> findByCustomer(Customer customer);
}