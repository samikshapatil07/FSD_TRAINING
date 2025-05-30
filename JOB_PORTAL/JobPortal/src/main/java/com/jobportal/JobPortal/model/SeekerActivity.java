package com.jobportal.JobPortal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seeker_activity")
public class SeekerActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long activityId;

    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType;

    private Long referenceId; // Could refer to jobId, applicationId, etc.

    private LocalDateTime timestamp;

    public enum ActivityType {
        LOGIN,
        LOGOUT,
        SEARCH,
        APPLY,
        UPDATE_RESUME,
        UPDATE_PROFILE
    }

    // Constructors
    public SeekerActivity() {
        this.timestamp = LocalDateTime.now();
    }

    public SeekerActivity(JobSeeker jobSeeker, ActivityType activityType, Long referenceId) {
        this.jobSeeker = jobSeeker;
        this.activityType = activityType;
        this.referenceId = referenceId;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public JobSeeker getJobSeeker() {
        return jobSeeker;
    }

    public void setJobSeeker(JobSeeker jobSeeker) {
        this.jobSeeker = jobSeeker;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

