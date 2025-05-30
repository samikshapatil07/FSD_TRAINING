
package com.springboot.lms.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.User;
import com.springboot.lms.service.UserService;
import com.springboot.lms.util.JwtUtil;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;
	/*
	 * AIM: Insert the user in the DB with password encrypted. 
	 * PATH: /api/user/signup
	 * PARAM: @RequestBody User user 
	 * Response: User 
	 * METHOD: POST 
	 * */
	@PostMapping("/signup")
	public User signUp(@RequestBody User user ) {
		return userService.signUp(user);
	}
	
	@GetMapping("/token")
	public String getToken(Principal principal) {
		System.out.println("I am in the API method");
		
		JwtUtil jwtUtil = new JwtUtil();
		return jwtUtil.createToken(principal.getName()); 
	}
}
