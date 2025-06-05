package com.jobportal.JobPortal.dto;

public class ApplicationUpdateDTO {

    private int updateId;
    private String updatedResumePath;
    private String updatedOn;
    private int applicationId;
    private int jobSeekerId;

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
