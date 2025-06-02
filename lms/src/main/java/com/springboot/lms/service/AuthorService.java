
package com.springboot.lms.service;

import org.springframework.stereotype.Service;

import com.springboot.lms.model.Author;
import com.springboot.lms.model.User;
import com.springboot.lms.repository.AuthorRepository;

@Service
public class AuthorService {

    private AuthorRepository authorRepository;
    private final UserService userService;

	public AuthorService(AuthorRepository authorRepository, UserService userService) {
		super();
		this.authorRepository = authorRepository;
		this.userService = userService;
	}
	//---------------------- add a new author ----------------------
	public Author postAuthor(Author author) {
        /** Assign role AUTHOR to this user */
        User user = author.getUser();
        user.setRole("AUTHOR");

        /* Fetch User from Author and add to DB */
        user = userService.signUp(author.getUser());

        /* Attach this user to author again */
        author.setUser(user);

        /* Activate Author - later let executive do this.. */
        author.setActive(true);

        /* Save Author in Db */
        return authorRepository.save(author);
    }


}
