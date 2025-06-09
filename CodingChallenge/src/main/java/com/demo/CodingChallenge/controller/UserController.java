package com.demo.CodingChallenge.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.User;
import com.demo.CodingChallenge.service.UserService;
import com.demo.CodingChallenge.util.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
    private Logger logger = LoggerFactory.getLogger("UserController");
    @Autowired
	private JwtUtil jwtUtil;

    
  //------------------------------- signup api ------------------
  	/*
  	 * AIM: Insert the user in the DB with password encrypted. 
  	 * PATH: /api/user/signup
  	 * PARAM: @RequestBody User user 
  	 * Response: User 
  	 * METHOD: POST 
  	 * */
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
            User createdUser = userService.signUp(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed: " + e.getMessage());
        }
    }
  //------------------------------- token api ------------------
  	/*
  	 * AIM: get token for valid user (username, password) 
  	 * PATH: /api/user/token 
  	 * Response: token 
  	 * METHOD: GET 
  	 * */
  	@GetMapping("/token")
  	public ResponseEntity<?> getToken(Principal principal) {
        logger.info("Generating token...");
  		try {
  		String token =jwtUtil.createToken(principal.getName()); 
  		return ResponseEntity.status(HttpStatus.OK).body(token);
  		}
  		catch(Exception e){
  			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
  		}
  	}
  	
  //--------------------- login api -------------
  	@GetMapping("/details")
  	public Object getLoggedInUserDetails(Principal principal) {
        logger.info("Getting user details..");
  		String username = principal.getName(); // loggedIn username
  		/**
  		 * Lets get the Role info of this User
  		 * As we dont know who the user really is? Learner? Author?
  		 */
  		Object object = userService.getUserInfo(username);
  		return object;
  	}
}
