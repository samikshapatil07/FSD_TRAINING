package com.jobportal.JobPortal.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    
 //------------------------- Register user ---------------------------------------------
    /*
	 * AIM : insert the user in the DB with password encryped
	 * PATH : /api/users/signup
	 * PARAM: @RequestBody User user
	 * RESPONSE: User
	 * METHOD: POST*/
	@PostMapping("/signup")
	public User SignUp(@RequestBody User user) {
		return userService.signUp(user);
		
	}
//----------------------------- Get user by ID ---------------------------------------------------------
    /*
     * AIM     : To get user details by ID
     * PATH    : /api/users/{id}
     * METHOD  : GET
     * INPUT   : id (path variable)
     * RESPONSE: User object
     */
    //@GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.getUserById(id));
//    }
    
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
	    return userService.getUserById(id);
	}
}
