package com.demo.CodingChallenge.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.CodingChallenge.model.User;
import com.demo.CodingChallenge.service.UserService;
import com.demo.CodingChallenge.util.JwtUtil;

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