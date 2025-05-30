package com.jobportal.JobPortal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "resume_history")
public class ResumeHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resumeId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @Column(nullable = false)
    private String filePath;

    private LocalDateTime uploadedOn;

    // Constructors
    public ResumeHistory() {
        this.uploadedOn = LocalDateTime.now();
    }

    public ResumeHistory(JobSeeker jobSeeker, String filePath) {
        this.jobSeeker = jobSeeker;
        this.filePath = filePath;
        this.uploadedOn = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getUploadedOn() {
        return uploadedOn;
    }

    public void setUploadedOn(LocalDateTime uploadedOn) {
        this.uploadedOn = uploadedOn;
    }
}
