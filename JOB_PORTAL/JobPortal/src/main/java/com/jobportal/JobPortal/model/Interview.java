package com.jobportal.JobPortal.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "interviews")
public class Interview {    //i in interview repository

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer interviewId;

    @Column(name = "interview_date", nullable = false)
    private LocalDate interviewDate;

    @Column(name = "interview_location")
    private String interviewLocation;

    @Enumerated(EnumType.STRING)
    @Column(name = "interview_mode", nullable = false)
    private InterviewMode interviewMode; // onl, offline
    
    @Enumerated(EnumType.STRING)
    @Column(name = "outcome", nullable = false)
    private InterviewOutcome outcome; // interview scheduled, interview completed_offered, interview completeed_rejected

    @ManyToOne
    @JoinColumn(name = "app_id", nullable = false)
    private Application application;
    
    public enum InterviewMode {
        ONLINE, OFFLINE
    }

    public enum InterviewOutcome {
    	PASSED,FAILED,PENDING
  
    }

	public Integer getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
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

	public InterviewMode getInterviewMode() {
		return interviewMode;
	}

	public void setInterviewMode(InterviewMode interviewMode) {
		this.interviewMode = interviewMode;
	}

	public InterviewOutcome getOutcome() {
		return outcome;
	}

	public void setOutcome(InterviewOutcome outcome) {
		this.outcome = outcome;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
    
}
