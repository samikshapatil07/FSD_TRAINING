package com.codingChallenge.project.controller;

import com.codingChallenge.project.model.User;
import com.codingChallenge.project.service.UserService;
import com.codingChallenge.project.utility.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/api/user/signUp")
    public ResponseEntity<?> signUp(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.signUp(user));
    }

    @GetMapping("/api/user/token")
    public ResponseEntity<?> getToken(Principal principal){
        try{
            String username = principal.getName();
            String token = jwtUtil.createToken(username);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
