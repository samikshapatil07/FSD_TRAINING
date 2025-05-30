package com.jobportal.JobPortal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "application")
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobPosting jobPosting;

    @Column(nullable = false)
    private String resumePath;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    private LocalDateTime appliedOn;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplicationUpdate> updates;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Interview> interviews;

    public enum Status {
        APPLIED,
        REJECTED,
        SHORTLISTED,
        INTERVIEW_SCHEDULED,
        INTERVIEW_COMPLETED_OFFERED,
        INTERVIEW_COMPLETED_REJECTED,
        OFFERED,INTERVIEW_COMPLETED
    }

    // Constructors
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

    // Getters and Setters
    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
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

    public List<ApplicationUpdate> getUpdates() {
        return updates;
    }

    public void setUpdates(List<ApplicationUpdate> updates) {
        this.updates = updates;
    }

    public List<Interview> getInterviews() {
        return interviews;
    }

    public void setInterviews(List<Interview> interviews) {
        this.interviews = interviews;
    }
}
