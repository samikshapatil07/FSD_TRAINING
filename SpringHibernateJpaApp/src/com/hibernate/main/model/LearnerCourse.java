package com.hibernate.main.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "learner_course")
public class LearnerCourse {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	@ManyToOne
	private Learner learner; //<-- learner_id will be added 
	
	@ManyToOne
	private Course course; //<-- course_id will be added
	
	@Column(name = "enrollment_date")
	private LocalDate enrollmentDate;
	
	@Column(name = "coupon_code")
	private String couponCode; 
	
	
}