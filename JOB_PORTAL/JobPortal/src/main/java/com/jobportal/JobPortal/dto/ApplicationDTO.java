package com.jobportal.JobPortal.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.JobSeeker;

public class ApplicationDTO {

    private int applicationId;
    private int jobSeekerId;
    private int jobId;
    private String resumePath;
    private String status;
    private LocalDateTime appliedOn;
    
    
    private JobPostingDTO jobPosting;  
    private JobSeekerDTO jobSeeker;
    
    
    //implementing dto by convertToDTO
    
    //here we are converting a list of applicationg entity objects into a list of applicationDTOs
    //we are using this when we have multiple application objects (List<application>) 
    //and we want to convert all  into DTOs to return as a list in response in API
    //we are using this for  getApplicationsByJobSeekerId ,getApplicationsByJobId in application service
    public static List<ApplicationDTO> converttoDto(List<ApplicationDTO> apps) {
        List<ApplicationDTO> listDto = new ArrayList<>();
        apps.stream().forEach(application -> {
            ApplicationDTO dto = new ApplicationDTO();
            dto.setApplicationId(application.getApplicationId());
            dto.setJobSeekerId(application.getJobSeeker().getJobSeekerId());
            dto.setJobId(application.getJobPosting().getJobId());
            
            
            dto.setResumePath(application.getResumePath());
            dto.setStatus(application.getStatus().toString());
            dto.setAppliedOn(application.getAppliedOn());
            
            // Now properly populate nested JobSeekerDTO
            JobSeekerDTO seeker = application.getJobSeeker();
            JobSeekerDTO seekerDto = new JobSeekerDTO();
            seekerDto.setJobSeekerId(seeker.getJobSeekerId());
            seekerDto.setName(seeker.getName());
            seekerDto.setEducation(seeker.getEducation());
            seekerDto.setSkills(seeker.getSkills());
            seekerDto.setExperience(seeker.getExperience());
            dto.setJobSeeker(seekerDto);
            
            listDto.add(dto);
            
            
        });

        return listDto;
    }
    
    //here we are converting a single application entity object into ApplicationDTO
    //we are using this when you have only one application object not a list
    //for updateApplicationStatus, updateApplication, saveApplication,  getApplicationById, createApplication,  in application service
    public static ApplicationDTO converttoDto(Application application) {
    	ApplicationDTO dto = new ApplicationDTO();
        dto.setApplicationId(application.getApplicationId());
        dto.setJobSeekerId(application.getJobSeeker().getJobSeekerId());
        dto.setJobId(application.getJobPosting().getJobId());
        dto.setResumePath(application.getResumePath());
        dto.setStatus(application.getStatus().toString());
        dto.setAppliedOn(application.getAppliedOn());
        
        // Now properly populate nested JobSeekerDTO
        JobSeeker seeker = application.getJobSeeker();
        JobSeekerDTO seekerDto = new JobSeekerDTO();
        seekerDto.setJobSeekerId(seeker.getJobSeekerId());
        seekerDto.setName(seeker.getName());
        seekerDto.setEducation(seeker.getEducation());
        seekerDto.setSkills(seeker.getSkills());
        seekerDto.setExperience(seeker.getExperience());
        
        dto.setJobSeeker(seekerDto);
		return dto;
    }

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getJobSeekerId() {
		return jobSeekerId;
	}

	public void setJobSeekerId(int jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getResumePath() {
		return resumePath;
	}

	public void setResumePath(String resumePath) {
		this.resumePath = resumePath;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(LocalDateTime appliedOn) {
		this.appliedOn = appliedOn;
	}

	public JobPostingDTO getJobPosting() {
		return jobPosting;
	}

	public void setJobPosting(JobPostingDTO jobPosting) {
		this.jobPosting = jobPosting;
	}

	public JobSeekerDTO getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeekerDTO jobSeeker) {
		this.jobSeeker = jobSeeker;
	}


}
