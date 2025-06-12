package com.jobportal.JobPortal.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.JobPortal.dto.UserDTO;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.service.UserService;
import com.jobportal.JobPortal.util.JwtUtil;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

	
    @Autowired
    private UserService userService;
    @Autowired
	private JwtUtil jwtUtil;
    
  //implementing logger
    private Logger logger = LoggerFactory.getLogger("UserController");

    
 //------------------------- Register user ---------------------------------------------
    /*
	 * AIM : insert the user in the DB with password encryped
	 * PATH : /api/users/signup
	 * PARAM: @RequestBody User user
	 * RESPONSE: User
	 * METHOD: POST*/
	@PostMapping("/signup")
	public User SignUp(@RequestBody User user) {
        logger.info("Registering User: " + user);
		return userService.signUp(user);
		
	}
	
	//----------------TOKEN API ----------------
	  /*
   * AIM     : To get token for valid users..
   * PATH    : /api/users/token}
   * METHOD  : GET
   */
	
	@GetMapping("/token")
	public ResponseEntity<?> getToken(Principal principal) {
		try {
		String token =jwtUtil.createToken(principal.getName()); 
		Map<String, Object> map = new HashMap<>();
		map.put("token",token );
		return ResponseEntity.status(HttpStatus.OK).body(map);
		}
		catch(Exception e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
	
	//--------------------- login api -------------
		@GetMapping("/details")
		public Object getLoggedInUserDetails(Principal principal) {
			String username = principal.getName(); // loggedIn username
			/**
			 * Lets get the Role info of this User
			 * As we dont know who the user really is? Learner? Author?
			 */
			Object object = userService.getUserInfo(username);
			return object;
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
	
	@GetMapping("/details/{id}")
	public UserDTO getUserById(@PathVariable int id) {
	    logger.info("Getting user details with id: " + id);
	    User user = userService.getUserById(id);
	    return new UserDTO(user.getId(), user.getUsername(), user.getRole());
	}
	

}
