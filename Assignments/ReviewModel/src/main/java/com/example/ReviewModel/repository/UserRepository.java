package com.example.ReviewModel.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ReviewModel.model.User;


public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

	User getByUsername(String username);
}