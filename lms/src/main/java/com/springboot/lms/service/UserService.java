package com.springboot.lms.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.lms.model.User;
import com.springboot.lms.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User signUp(User user) {
		// encrypt the pain text password given 
		String plainPassword = user.getPassword(); //<- this gives you plain password
		String encodedPassword =  passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword); //<- Now, User has encoded password 
		
		// Save User in DB 
		return userRepository.save(user);
	}
	
	
}