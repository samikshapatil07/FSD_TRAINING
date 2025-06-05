package com.jobportal.JobPortal.dto;

import java.time.LocalDateTime;

public class JobPostingDTO {

    private int jobId;
    private String jobTitle;
    private String description;
    private String skills;
    private String location;
    private int salary;
    private String department;
    private String company;
    private String experience;
    private LocalDateTime createdAt;

    public JobPostingDTO() {
    }

    public JobPostingDTO(int jobId, String jobTitle, String description, String skills, 
                         String location, int salary, String department, String company,
                         String experience, LocalDateTime createdAt) {
        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.description = description;
        this.skills = skills;
        this.location = location;
        this.salary = salary;
        this.department = department;
        this.company = company;
        this.experience = experience;
        this.createdAt = createdAt;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
