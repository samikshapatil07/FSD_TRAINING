package com.jobportal.JobPortal.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "job_posting")
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  jobId;

    @ManyToOne
    @JoinColumn(name = "hr_id")  
    private Hr hr;
   
 
    private String jobTitle;
    private String description;
    private String skills;
    private String location;
    private int salary;
    private String department;
    private String company;
    private String experience;
    private LocalDateTime createdAt;


    public JobPosting() {
        this.createdAt = LocalDateTime.now();
    }

    public JobPosting(String jobTitle, String description, String skills, 
    		String location, int salary, String department, String company,
    		String experience, Hr hr) {
        this.jobTitle = jobTitle;
        this.description = description;
        this.skills = skills;
        this.location = location;
        this.salary = salary;
        this.department = department;
        this.company = company;
        this.experience = experience;
        this.createdAt = LocalDateTime.now();
        this.hr = hr;
    }

    
    public int getJobId() {
        return jobId;
    }

    public void setJobId(int i) {
        this.jobId = i;
    }

    public Hr getHr() {
        return hr;
    }

    public void setHr(Hr hr) {
        this.hr = hr;
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

