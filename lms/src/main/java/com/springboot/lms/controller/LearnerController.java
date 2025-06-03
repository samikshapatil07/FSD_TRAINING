
package com.springboot.lms.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private Logger logger = LoggerFactory.getLogger("LearnerController");

    /*
     * PATH: http://localhost:8080/api/learner/add
     * Response: Learner with User details
     * PARAM: @RequestBody Learner learner
     * METHOD: POST
     * AIM: I want to add learner in Db along with its user credentails
     * so that learner can login later.
     */
    @PostMapping("/api/learner/add")
    public Learner insertLearner(@RequestBody Learner learner) {
        return learnerService.insertLearner(learner);
    }

    /*
     * AIM: To fetch all learner records
     * PATH: /api/learner/get-all
     * Method: GET
     * Response: List<Learner>
     */
    @GetMapping("/api/learner/get-all")
    public ResponseEntity<?> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(learnerService.getAll());
    }

    /*
     * AIM: To delete learner by id
     * PATH: /api/learner/delete
     * METHOD: delete
     * response: void
     * Input: id - PathVariable
     */
    @DeleteMapping("/api/learner/delete/{id}")
    public ResponseEntity<?> deleteLearner(@PathVariable int id) {
        learnerService.deleteLearner(id);
        return ResponseEntity.status(HttpStatus.OK).body("Learner deleted");
    }

    /*
     * AIM: Get Learner using loggedIn credentials
     * PATH: /api/learner/get-one
     * Method: GET
     * Response: Learner
     */
    @GetMapping("/api/learner/get-one")
    public Learner getLearnerById(Principal principal) {
        // Ask spring username of loggedIn user using Principal interface
        String username = principal.getName();
        return learnerService.getLearnerByUsername(username);
    }
//---------------------update learner (used logger) ---------------
    /*
     * AIM: Update Learner
     * PATH: /api/learner/update/{id}
     * Body: updated learner
     * METHOD: PUT
     * response: updated learner
     * input: id as path variable , learner as request body
     */
    @PutMapping("/api/learner/update/{id}")
    public Learner updateLearner(@PathVariable int id, @RequestBody Learner updatedLearner) {
        logger.info("ID given is : " + id);
        return learnerService.updateLearner(id, updatedLearner);
    }
}
/*
 * {
 * "name":"Harry Potter",
 * "contact" : "9866746558"
 * }
 * 
 * Jackson Dependency
 * 
 * Learner learner = new Learner();
 * learner.setName(name)
 * learner.setContact(contact)
 * 
 */
