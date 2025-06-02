package com.springboot.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Author;
import com.springboot.lms.service.AuthorService;

@RestController
@RequestMapping("/api/author")
public class AuthorController {
	 @Autowired
	    private AuthorService authorService;

     //----------------------- ADD AUTHOR ---------------------------
    /*
     * PATH: http://localhost:8080/api/author/add
     * Response: Learner with User details
     * PARAM: @RequestBody Learner learner
     * METHOD: POST 
     * AIM: I want to add learner in Db along with its user credentails
     * so that learner can login later. 
     * */
    @PostMapping("/add")
    public Author PostAuthor(@RequestBody Author author) {
        return authorService.postAuthor(author);
    }

}