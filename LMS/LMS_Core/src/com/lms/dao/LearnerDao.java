
package com.lms.dao;

import java.util.List;

import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;

public interface LearnerDao {
	/* CRUD Ops*/
	List<Learner> getAll();
	Learner getById(int id) throws InvalidIdException;
	void deleteById(int id) throws InvalidIdException;
	Learner update(int id, Learner learner) throws InvalidIdException,InvalidInputException; 
	void insert(Learner learner) throws InvalidInputException; 
}
