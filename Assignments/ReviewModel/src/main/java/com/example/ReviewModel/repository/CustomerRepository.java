package com.example.ReviewModel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.example.ReviewModel.model.Customer;
import com.example.ReviewModel.model.User;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query
	("SELECT c FROM Customer c WHERE c.user.username =?1 ")
	Customer getCustomerByUsername(String username);

	Optional<Customer> findByUser(User user);	
}
