package com.jobportal.JobPortal.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.JobPortal.model.JobPosting;
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
    
    
    //implementing dto by convertToDTO
    
    //here we are converting a list of Job posting entity objects into a list of JobPostingDTOs
    //we are using this when we have multiple job posting objects (List<JobPosting>) 
    //and we want to convert all  into DTOs to return as a list in response in API
    //we are using this for , getAllJobs in job posting service
    public static List<JobPostingDTO> converttoDto(List<JobPosting> list) {
        List<JobPostingDTO> listDto = new ArrayList<>();
        list.stream().forEach(jobPosting -> {
        	JobPostingDTO dto = new JobPostingDTO();
            dto.setJobId(jobPosting.getJobId());
            dto.setJobTitle(jobPosting.getJobTitle());
            dto.setDescription(jobPosting.getDescription());
            dto.setSkills(jobPosting.getSkills());
            dto.setLocation(jobPosting.getLocation());
            dto.setSalary(jobPosting.getSalary());
            dto.setDepartment(jobPosting.getDepartment());
            dto.setCompany(jobPosting.getCompany());
            dto.setExperience(jobPosting.getExperience());
            dto.setCreatedAt(jobPosting.getCreatedAt());
            
            listDto.add(dto);
        });

        return listDto;
    }
    
    //here we are converting a single JobPosting entity object into JobPostingDTO
    //we are using this when you have only one JobPosting object not a list
    //for , getJobById, in job posting service
    public static JobPostingDTO converttoDto(JobPosting jobPosting) {
    	JobPostingDTO dto = new JobPostingDTO();
        dto.setJobId(jobPosting.getJobId());
        dto.setJobTitle(jobPosting.getJobTitle());
        dto.setDescription(jobPosting.getDescription());
        dto.setSkills(jobPosting.getSkills());
        dto.setLocation(jobPosting.getLocation());
        dto.setSalary(jobPosting.getSalary());
        dto.setDepartment(jobPosting.getDepartment());
        dto.setCompany(jobPosting.getCompany());
        dto.setExperience(jobPosting.getExperience());
        dto.setCreatedAt(jobPosting.getCreatedAt());
        return dto;
    }
    
      

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
