package com.jobportal.JobPortal.dto;

import java.util.ArrayList;
import java.util.List;

import com.jobportal.JobPortal.model.ApplicationUpdate;
public class ApplicationUpdateDTO {

    private int updateId;
    private String updatedResumePath;
    private String updatedOn;
    private int applicationId;
    private int jobSeekerId;

    public static List<ApplicationUpdateDTO> converttoDto(List<ApplicationUpdate> list) {
        List<ApplicationUpdateDTO> listDto = new ArrayList<>();
        list.stream().forEach(applicationUpdate -> {
        	ApplicationUpdateDTO dto = new ApplicationUpdateDTO();
            dto.setUpdateId(applicationUpdate.getUpdateId());
            dto.setUpdatedResumePath(applicationUpdate.getUpdatedResumePath());
            dto.setUpdatedOn(applicationUpdate.getUpdatedOn().toString());
            dto.setApplicationId(applicationUpdate.getApplication().getApplicationId());
            dto.setJobSeekerId(applicationUpdate.getJobSeeker().getJobSeekerId());
            listDto.add(dto);
        });

        return listDto;
    }
    
    public int getUpdateId() {
        return updateId;
    }

    public void setUpdateId(int updateId) {
        this.updateId = updateId;
    }

    public String getUpdatedResumePath() {
        return updatedResumePath;
    }

    public void setUpdatedResumePath(String updatedResumePath) {
        this.updatedResumePath = updatedResumePath;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
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
}
