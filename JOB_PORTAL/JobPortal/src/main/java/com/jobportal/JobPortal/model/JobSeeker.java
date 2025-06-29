package com.jobportal.JobPortal.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "job_seeker")
public class JobSeeker { //js 

    @Id                  //Primary key - auto-generated job seeker ID 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_seeker_id")
    private int jobSeekerId;
    
    private String name;
    
    private String education;
    
    private String skills;
    
    private String experience;

 
    @OneToOne //'user_id' column is the foreign key in the job_seeker table.
    @JoinColumn(name = "user_id")
    private User user;


	@Override
	public String toString() {
		return "JobSeeker [jobSeekerId=" + jobSeekerId + ", name=" + name + ", education=" + education + ", skills="
				+ skills + ", experience=" + experience + ", user=" + user + "]";
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



}
