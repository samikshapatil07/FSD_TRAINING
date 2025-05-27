package com.springboot.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.lms.model.Learner;

public interface LearnerRepository extends JpaRepository<Learner, Integer>{

}

/*
 * Spring Data JPA 
 * ----------------
 * JpaRepository -- Interface 
 * lets say, T is object
 * 
 * methods are:
 * save(T) : T
 * findById(id) : T
 * findAll(): List<T>
 * deleteById(id): void
 * delete(T): void
 * 
 * 
 */