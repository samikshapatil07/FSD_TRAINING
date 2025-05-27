package com.springboot.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.lms.model.Learner;
import com.springboot.lms.service.LearnerService;

@RestController
public class LearnerController {

	@Autowired
	private LearnerService learnerService;
//------------------- add earner-------------------------
	/*AIM: To add learner record
	 *PaTH: /api/learner/add
	 *METHOD: POST */
	@PostMapping("/api/learner/add")
	public Learner insertLearner(@RequestBody Learner learner) {
		return  learnerService.insertLearner(learner); 
	}
	
//------------------- get all learner-------------------------
	/*AIM: To fetch all learner record
	 *PaTH: /api/learner/get-all
	 *METHOD: GET
	 *Response: List<learner */
	@GetMapping("/api/learner/get-all")
	public List<Learner> getAll(){
		return learnerService.getAll();
		
	}	
//------------------- delete learner by id -------------------------
     /*
      * AIM: to delete learner by id
      * PATH: /api/learner/delete-by-id
      * METHOD: DELETE
      * Response: void
      * INPUT: id -pathvariable
      * */
	@DeleteMapping("/api/learner/delete/{id}")
	public void deleteLearner(@PathVariable int id) {
		learnerService.deleteLearner(id);
	}
//------------------- get learner by id -------------------------
    /*
     * AIM: to get learner by id
     * PATH: /api/learner/get-one/{id}
     * METHOD: GET
     * Response: Learner
     * INPUT: id 
     * */
	@GetMapping("/api/learner/get-one/{id}")
	public Learner getLearnerById(@PathVariable int id) {
		return learnerService.getLearnerById(id);
	}
//------------------- update learner -------------------------
/*
 *    AIM: to update learner 
     * PATH: /api/learner/update/{id}
     * BODY: updated learner
     * METHOD: PUT
     * Response: updated learner
     * INPUT: id as path variable, learner as request body
     * */
	@PutMapping("/api/learner/update/{id}")
	public Learner updateLearner(@PathVariable int id, @RequestBody Learner updatedLearner) {
		return learnerService.updateLearner(id,updatedLearner);
	}
}





/* 
 * {
    "name":Samiksha Patil",
    "contact" : "7097381008"
	}
	
	Jackson Dependency 
	
	Learner learner = new Learner(); 
	learner.setName(name)
	learner.setContact(contact)
 * 
 */