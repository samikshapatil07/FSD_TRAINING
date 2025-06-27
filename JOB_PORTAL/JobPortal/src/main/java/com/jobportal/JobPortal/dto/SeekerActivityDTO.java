package com.jobportal.JobPortal.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jobportal.JobPortal.model.SeekerActivity;
import com.jobportal.JobPortal.model.SeekerActivity.ActivityType;

public class SeekerActivityDTO {

    private int activityId;
    private ActivityType activityType;
    private String description;
    private LocalDateTime timestamp;
    private int jobSeekerId;
    
    //we are using this for  getApplicationsByJobSeekerId ,getApplicationsByJobId in application service
    public static List<SeekerActivityDTO> converttoDto(List<SeekerActivity> list) {
        List<SeekerActivityDTO> listDto = new ArrayList<>();
        list.stream().forEach(seekerActivityDTO -> {
        	SeekerActivityDTO dto = new SeekerActivityDTO();
            dto.setActivityId(seekerActivityDTO.getActivityId());
            dto.setActivityType(seekerActivityDTO.getActivityType());
            dto.setDescription(seekerActivityDTO.getDescription());
            dto.setTimestamp(seekerActivityDTO.getTimestamp());
            dto.setJobSeekerId(seekerActivityDTO.getJobSeeker().getJobSeekerId());
            
            listDto.add(dto);
        });

        return listDto;
    }
    
    //for updateApplicationStatus, updateApplication, saveApplication,  getApplicationById, createApplication,  in application service
    public static SeekerActivityDTO converttoDto(SeekerActivity seekerActivityDTO) {
    	SeekerActivityDTO dto = new SeekerActivityDTO();
    	 dto.setActivityId(seekerActivityDTO.getActivityId());
         dto.setActivityType(seekerActivityDTO.getActivityType());
         dto.setDescription(seekerActivityDTO.getDescription());
         dto.setTimestamp(seekerActivityDTO.getTimestamp());
         dto.setJobSeekerId(seekerActivityDTO.getJobSeeker().getJobSeekerId());
        return dto;
    }
    

    public SeekerActivityDTO() {
    }

    public SeekerActivityDTO(int activityId, ActivityType activityType, String description,
                              LocalDateTime timestamp, int jobSeekerId) {
        this.activityId = activityId;
        this.activityType = activityType;
        this.description = description;
        this.timestamp = timestamp;
        this.jobSeekerId = jobSeekerId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(int jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }
}
