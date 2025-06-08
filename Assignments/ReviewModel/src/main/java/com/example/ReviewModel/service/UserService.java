
package com.example.ReviewModel.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ReviewModel.dto.UserDTO;
import com.example.ReviewModel.model.Customer;
import com.example.ReviewModel.model.User;
import com.example.ReviewModel.repository.CustomerRepository;
import com.example.ReviewModel.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private final CustomerRepository customerRepository;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			CustomerRepository customerRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.customerRepository = customerRepository;
	}
//-------------------------sign up user---------------------
	public User signUp(User user) {
		// encrypt the pain text password given
		String plainPassword = user.getPassword(); // <- this gives you plain password
		String encodedPassword = passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword); // <- Now, User has encoded password
         //check if user exists
		if (userRepository.findByUsername(user.getUsername()) != null) {
		    throw new RuntimeException("Username already exists");
		}
		// Save User in DB
		return userRepository.save(user);
	}

//--------------------get user info-------------------
	public Object getUserInfo(String username) {
	    User user = userRepository.findByUsername(username)
	                  .orElseThrow(() -> new RuntimeException("User not found"));

	    // Map basic info into DTO
	    UserDTO dto = new UserDTO();
	    dto.setUsername(user.getUsername());
	    dto.setRole(user.getRole());

	    switch (user.getRole().toUpperCase()) {
	        case "CUSTOMER":
	            Customer customer = customerRepository.getCustomerByUsername(username);
	            if (customer != null) {
	                dto.setName(customer.getName());
	                dto.setContact(customer.getContact());
	            }
	            return dto;

	        case "ADMIN":
	            return dto;

	        default:
	            throw new RuntimeException("Unsupported role: " + user.getRole());
	    }
	}


}