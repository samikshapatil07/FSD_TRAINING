package com.jobportal.JobPortal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "interview")
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interviewId;

    @ManyToOne
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    private LocalDate interviewDate;
    private String interviewLocation;

    @Enumerated(EnumType.STRING)
    private Mode interviewMode;

    private String meetingLink;

    @Enumerated(EnumType.STRING)
    private Outcome outcome;

    private String feedback;

    public enum Mode { ONLINE, OFFLINE, PHONE }
    public enum Outcome { PASSED, FAILED, PENDING }

    // Constructors
    public Interview() {
        this.outcome = Outcome.PENDING;
    }

    public Interview(Application application, LocalDate interviewDate, String interviewLocation, Mode interviewMode, String meetingLink) {
        this.application = application;
        this.interviewDate = interviewDate;
        this.interviewLocation = interviewLocation;
        this.interviewMode = interviewMode;
        this.meetingLink = meetingLink;
        this.outcome = Outcome.PENDING;
    }

    // Getters and Setters
    public Long getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Long interviewId) {
        this.interviewId = interviewId;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }

    public Mode getInterviewMode() {
        return interviewMode;
    }

    public void setInterviewMode(Mode interviewMode) {
        this.interviewMode = interviewMode;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
