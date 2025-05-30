package com.jobportal.JobPortal.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "application_update")
public class ApplicationUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long updateId;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Column(nullable = false)
    private String updatedResumePath;
    
    @Column(unique = true)
    private LocalDateTime updatedOn;

    // Constructors
    public ApplicationUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

    public ApplicationUpdate(Application application, String updatedResumePath) {
        this.application = application;
        this.updatedResumePath = updatedResumePath;
        this.updatedOn = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getUpdatedResumePath() {
        return updatedResumePath;
    }

    public void setUpdatedResumePath(String updatedResumePath) {
        this.updatedResumePath = updatedResumePath;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(LocalDateTime updatedOn) {
        this.updatedOn = updatedOn;
    }
}
