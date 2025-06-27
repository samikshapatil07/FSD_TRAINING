package com.jobportal.JobPortal;

import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.UserRepository;
import com.jobportal.JobPortal.service.UserService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User();
        user.setId(1);
        user.setUsername("john_doe");
        user.setPassword("plaintext123");
        user.setRole("HR");
    }

    @Test // <<<<--- Register user and encode password
    public void signUpTest() {
        // expected output
        String encodedPassword = "encoded123";
        when(passwordEncoder.encode("plaintext123")).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        // actual output
        User saved = userService.signUp(user);

        assertEquals(encodedPassword, saved.getPassword());
        assertEquals("HR", saved.getRole());
        verify(passwordEncoder).encode("plaintext123");
        verify(userRepository).save(user);
    }

    
    // After each test case, the objects used in them will get nullified and HEAP
    // memory will be free
	@AfterEach
    public void afterTest() {
        user = null;
        System.out.println("jobSeeker object released.." + user);
    }
}