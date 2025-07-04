package com.jobportal.JobPortal.model;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "application_updates")
public class ApplicationUpdate {  //au in repository for jpql

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int updateId;

    @Column(name = "updated_resume_path", nullable = false)
    private String updatedResumePath;

    @Column(name = "updated_on", nullable = false)
    private Timestamp updatedOn;

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private Application application;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdatedResumePath() {
        return updatedResumePath;
    }

    public void setUpdatedResumePath(String updatedResumePath) {
        this.updatedResumePath = updatedResumePath;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }
}
