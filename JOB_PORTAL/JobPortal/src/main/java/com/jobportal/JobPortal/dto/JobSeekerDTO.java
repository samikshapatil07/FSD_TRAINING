package com.jobportal.JobPortal.dto;

import java.util.ArrayList;
import java.util.List;

import com.jobportal.JobPortal.model.JobSeeker;

public class JobSeekerDTO {
    private int jobSeekerId;
    private String name;
    private String education;
    private String skills;
    private String experience;
    private String resume;
    private String username;
    
    
   //implementing dto by convertToDTO
    //here we are converting a list of JobSeeker entity objects into a list of JobSeekerDTOs
    //we are using this when we have multiple JobSeeker objects (List<JobSeeker>) 
    //and we want to convert all  into DTOs to return as a list in response in API
    //we are using this for , getAllJobSeekers in js service
    public static List<JobSeekerDTO> converttoDto(List<JobSeeker> list) {
    	//Initializes an empty list to hold the DTO objects.
        List<JobSeekerDTO> listDto = new ArrayList<>();
        //Loops through each JobSeeker entity in the list using a stream.
        list.stream().forEach(jobSeeker -> {
        	//creates a new DTO object for each entity.
        	JobSeekerDTO dto = new JobSeekerDTO();
            dto.setJobSeekerId(jobSeeker.getJobSeekerId());
            dto.setName(jobSeeker.getName());
            dto.setEducation(jobSeeker.getEducation());
            dto.setSkills(jobSeeker.getSkills());
            dto.setExperience(jobSeeker.getExperience());
            dto.setUsername(jobSeeker.getUser().getUsername());
            //adds each newly created DTO to the result list.
            listDto.add(dto);
        });

        return listDto;//returns the final list of DTOs.
    }
    
    //here we are converting a single JobSeeker entity object into JobSeekerDTO
    //we are using this when you have only one JobSeeker object not a list
    //for getJobSeekerByUsername, getJobSeekerById,getJobSeekerByAppId in js service
    public static JobSeekerDTO converttoDto(JobSeeker jobSeeker) {
        JobSeekerDTO dto = new JobSeekerDTO();
        dto.setJobSeekerId(jobSeeker.getJobSeekerId());
        dto.setName(jobSeeker.getName());
        dto.setEducation(jobSeeker.getEducation());
        dto.setSkills(jobSeeker.getSkills());
        dto.setExperience(jobSeeker.getExperience());
        dto.setUsername(jobSeeker.getUser().getUsername());
        return dto;
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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
