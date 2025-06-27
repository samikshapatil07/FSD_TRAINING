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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.service.UserService;
import com.jobportal.JobPortal.util.JwtUtil;

//this controller handles : login, signup, getting token
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173") // Allows requests from frontend hosted at this origin
public class UserController {

	
    @Autowired
    private UserService userService;
    @Autowired
	private JwtUtil jwtUtil; // for generating JWT token
    //JWT and user roles are handled by the service and JwtUtil
    
  //implementing logger to tarck activity
    private Logger logger = LoggerFactory.getLogger("UserController");

    
 //------------------------- SignUp user ---------------------------------------------
    /* USED IN --------> components/SignUp 
     * when a new user digns up, this api is called..it receives a user obejct from FE(username, pswd, role) and 
     * then it saves the user after encrypting the password and returns the saved user
	 * AIM : insert the user in the DB with password encryped
	 * PATH : /api/user/signup
	 * PARAM: @RequestBody User user
	 * RESPONSE: User
	 * METHOD: POST
	 * */
	@PostMapping("/signup")
	public User SignUp(@RequestBody User user) {
        logger.info("Registering User: " + user);
		return userService.signUp(user);
		
	}
	
//----------------------------------- login api -----------------------------------------------------------
    /*USED IN --------> components/Login
     * This endpoint is used to get details of the currently logged-in user.
     * It returns different data depending on whether the user is an HR, job seeker, or Executive.
     * 
     * path: /api/user/details
     * Method  : GET
     * Input   : Principal 
     * Output  : Full user object with associated role-specific info
     */
	
	@GetMapping("/details")
	public Object getLoggedInUserDetails(Principal principal) {
		String username = principal.getName(); // loggedIn username
		/**
		 * Lets get the Role info of this User
		 * As we dont know who the user really is? JS? HR?
		 */
		Object object = userService.getUserInfo(username);
		return object;
	}
	
//----------------------------TOKEN API ---------------------------------------------------
	 
	 /* USED IN --------> components/Loginafter a successful login , this generates a jwT token for the logged in user
	   * this token is used for authentication in furthur requests
   * AIM     : To get token for valid users..
   * PATH    : /api/user/token}
   * METHOD  : GET
   * * Input   : Logged-in user info via Principal
     * Output  : JWT token inside a JSON response
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
	
}



















		
//----------------------------- Get user by ID ---------------------------------------------------------
    /*
     * AIM     : To get user details by ID
     * PATH    : /api/user/{id}
     * METHOD  : GET
     * INPUT   : id (path variable)
     * RESPONSE: User object
     */
    //@GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return ResponseEntity.ok(userService.getUserById(id));
//    }
	
//	@GetMapping("/details/{id}")
//	public UserDTO getUserById(@PathVariable int id) {
//	    logger.info("Getting user details with id: " + id);
//	    User user = userService.getUserById(id);
//	    return new UserDTO(user.getId(), user.getUsername(), user.getRole());
//	}
//	


