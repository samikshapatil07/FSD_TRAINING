package com.codingChallenge.project.repository;

import com.codingChallenge.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("Select u from User u WHERE u.username = ?1")
    User getByUsername(String username);
}
