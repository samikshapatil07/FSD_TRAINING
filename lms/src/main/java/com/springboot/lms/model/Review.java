package com.springboot.lms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; //findById <--- in build method
	
	@Column(length = 1000)
	private String comment; // <--- not build method 
	                        //so declare findByComment(String comment)
	
	private String rating; //<--- not build method
	                       //so declare findByRating(String rating)
	
	@ManyToOne
	private LearnerCourse learnerCourse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public LearnerCourse getLearnerCourse() {
		return learnerCourse;
	}

	public void setLearnerCourse(LearnerCourse learnerCourse) {
		this.learnerCourse = learnerCourse;
	} 

}