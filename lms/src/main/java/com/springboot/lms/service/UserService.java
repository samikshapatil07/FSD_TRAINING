
package com.springboot.lms.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.Learner;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.AuthorRepository;
import com.springboot.lms.repository.LearnerRepository;
import com.springboot.lms.repository.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private LearnerRepository learnerRepository;
	private AuthorRepository authorRepository;




	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
			LearnerRepository learnerRepository, AuthorRepository authorRepository) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.learnerRepository = learnerRepository;
		this.authorRepository = authorRepository;
	}

	public User signUp(User user) {
		// encrypt the pain text password given
		String plainPassword = user.getPassword(); // <- this gives you plain password
		String encodedPassword = passwordEncoder.encode(plainPassword);
		user.setPassword(encodedPassword); // <- Now, User has encoded password

		// Save User in DB
		return userRepository.save(user);
	}

	public Object getUserInfo(String username) {
		User user = userRepository.findByUsername(username);
		switch (user.getRole().toUpperCase()) {
			case "LEARNER":
				Learner learner = learnerRepository.getLearnerByUsername(username);
				return learner;
			case "AUTHOR":
				Author author = authorRepository.getAuthorByUsername(username);
				if(author.isActive())
				return author;
				else
					throw new RuntimeException("Author Inactive");			
				case "EXECUTIVE":
				return null;
			default:
				return null;
		}

	}

}
