package com.jobportal.JobPortal.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "application")
public class Application { //ap

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int applicationId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobPosting jobPosting;
    
    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationUpdate> updates;


    @Column(nullable = false)
    private String resumePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status; // applied  by default, hr can change status to rejected 

    @Column(name = "applied_on")
    private LocalDateTime appliedOn;

    //  to represent current application status we are using enum
    public enum Status {
    	//for application
    	   APPLIED,APPLICATION_REJECTED, 
    	   //for interview
    	   INTERVIEW_SCHEDULED,OFFERED,REJECTED    
    }

    public Application() {
        this.appliedOn = LocalDateTime.now();
        this.status = Status.APPLIED;
    }

    public Application(JobSeeker jobSeeker, JobPosting jobPosting, String resumePath) {
        this.jobSeeker = jobSeeker;
        this.jobPosting = jobPosting;
        this.resumePath = resumePath;
        this.appliedOn = LocalDateTime.now();
        this.status = Status.APPLIED;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public void setJobPosting(JobPosting jobPosting) {
        this.jobPosting = jobPosting;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getAppliedOn() {
        return appliedOn;
    }

    public void setAppliedOn(LocalDateTime appliedOn) {
        this.appliedOn = appliedOn;
    }
}
