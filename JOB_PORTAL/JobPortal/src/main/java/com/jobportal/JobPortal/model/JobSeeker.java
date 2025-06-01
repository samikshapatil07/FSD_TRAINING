package com.jobportal.JobPortal.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "job_seeker")
public class JobSeeker { //js

    @Id //Primary key - auto-generated job seeker ID 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_seeker_id")
    private Long jobSeekerId;
    
    @Column(nullable = false)
    private String name;
    
    @Column(name = "education", nullable = false)
    private String education;
    
    @Column(name = "skills", nullable = false)
    private String skills;
    
    @Column(name = "experience", nullable = false)
    private String experience;

    @OneToOne(cascade = CascadeType.ALL) //'user_id' column is the foreign key in the job_seeker table.
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
    private List<SeekerActivity> activities;

	@Override
	public String toString() {
		return "JobSeeker [jobSeekerId=" + jobSeekerId + ", name=" + name + ", education=" + education + ", skills="
				+ skills + ", experience=" + experience + ", user=" + user + "]";
	}

	public Long getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(Long jobSeekerId) {
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
