
package com.lms.model;

import java.time.LocalDate;

import com.lms.enums.Coupon;

public class Enroll {

	//inject learner 
	Learner learner; 
	//inject course
	Course course;
	private LocalDate dateOfEnroll; 
	private Coupon coupon;
	private String feePaid;
	
	public Enroll() { }
	
	 

	public Enroll(Learner learner, Course course, LocalDate dateOfEnroll, Coupon coupon, String feePaid) {
		super();
		this.learner = learner;
		this.course = course;
		this.dateOfEnroll = dateOfEnroll;
		this.coupon = coupon;
		this.feePaid = feePaid;
	}



	public Learner getLearner() {
		return learner;
	}

	public void setLearner(Learner learner) {
		this.learner = learner;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public LocalDate getDateOfEnroll() {
		return dateOfEnroll;
	}

	public void setDateOfEnroll(LocalDate dateOfEnroll) {
		this.dateOfEnroll = dateOfEnroll;
	}

 
	public String getFeePaid() {
		return feePaid;
	}

	public void setFeePaid(String feePaid) {
		this.feePaid = feePaid;
	}



	public Coupon getCoupon() {
		return coupon;
	}



	public void setCoupon(Coupon coupon) {
		this.coupon = coupon;
	}



	@Override
	public String toString() {
		return "Enroll [learner=" + learner + ", course=" + course + ", dateOfEnroll=" + dateOfEnroll + ", coupon="
				+ coupon + ", feePaid=" + feePaid + "]";
	}

	 
	
	
}
