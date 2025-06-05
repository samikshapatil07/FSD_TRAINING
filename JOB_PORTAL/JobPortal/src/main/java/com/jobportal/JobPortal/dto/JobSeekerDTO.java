package com.jobportal.JobPortal.dto;

public class JobSeekerDTO {
    private int jobSeekerId;
    private String name;
    private String education;
    private String skills;
    private String experience;
    private int userId;
    

	public JobSeekerDTO() {
	}
	public JobSeekerDTO(int jobSeekerId, String name, String education, String skills, String experience, int userId) {
		super();
		this.jobSeekerId = jobSeekerId;
		this.name = name;
		this.education = education;
		this.skills = skills;
		this.experience = experience;
		this.userId = userId;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}


}
