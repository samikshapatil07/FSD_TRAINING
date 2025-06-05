package com.jobportal.JobPortal;

import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.UserRepository;
import com.jobportal.JobPortal.service.UserService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

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

    @Test // <<<< Register user and encode password
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

    @Test // <<<< Get user by ID
    public void getUserByIdTest() {
        // expected output
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        // actual output
        User actual = userService.getUserById(1);

        assertEquals("john_doe", actual.getUsername());
        assertEquals("HR", actual.getRole());
        verify(userRepository).findById(1);
    }

    @Test // <<<< User not found -> throws exception
    public void getUserById_NotFound_ShouldThrowException() {
        // expected output
        when(userRepository.findById(99)).thenReturn(Optional.empty());

        // actual output
        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(99));
        verify(userRepository).findById(9);
    }
}
