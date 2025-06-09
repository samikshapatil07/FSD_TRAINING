package com.codingChallenge.project.repository;

import com.codingChallenge.project.model.Patient;
import com.codingChallenge.project.model.User;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
    List<Patient> user(User user);

    @Query("Select p from Patient p WHERE p.user.username = ?1")
    Patient getByUsername(String username);
    
    @Query
    ("Select")
    
    
}
