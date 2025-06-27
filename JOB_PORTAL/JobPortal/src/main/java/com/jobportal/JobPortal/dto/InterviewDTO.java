package com.jobportal.JobPortal.dto;

import java.util.ArrayList;
import java.util.List;

import com.jobportal.JobPortal.model.Interview;

public class InterviewDTO {
    
    private int interviewId;
    private String interviewDate;
    private String interviewLocation;
    private String interviewMode;
    private String outcome;
    private int applicationId;
    
    //implementing dto by convertToDTO
    
    //we are using this for  getApplicationsByJobSeekerId ,getApplicationsByJobId in application service
    public static List<InterviewDTO> converttoDto(List<Interview> list) {
        List<InterviewDTO> listDto = new ArrayList<>();
        list.stream().forEach(interview -> {
        	InterviewDTO dto = new InterviewDTO();
            dto.setInterviewId(interview.getInterviewId());
            dto.setInterviewDate(interview.getInterviewDate().toString());
            dto.setInterviewLocation(interview.getInterviewLocation());
            dto.setInterviewMode(interview.getInterviewMode().toString());
            dto.setOutcome(interview.getOutcome().toString());
            dto.setApplicationId(interview.getApplication().getApplicationId());
            
            listDto.add(dto);
        });

        return listDto;
    }
    
    //for updateApplicationStatus, updateApplication, saveApplication,  getApplicationById, createApplication,  in application service
    public static InterviewDTO converttoDto(Interview interview) {
    	InterviewDTO dto = new InterviewDTO();
        dto.setInterviewId(interview.getInterviewId());
        dto.setInterviewDate(interview.getInterviewDate().toString());
        dto.setInterviewLocation(interview.getInterviewLocation());
        dto.setInterviewMode(interview.getInterviewMode().toString());
        dto.setOutcome(interview.getOutcome().toString());
        dto.setApplicationId(interview.getApplication().getApplicationId());
        return dto;
    }
    
    
    public Integer getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(Integer interviewId) {
        this.interviewId = interviewId;
    }

    public String getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(String interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewLocation() {
        return interviewLocation;
    }

    public void setInterviewLocation(String interviewLocation) {
        this.interviewLocation = interviewLocation;
    }

    public String getInterviewMode() {
        return interviewMode;
    }

    public void setInterviewMode(String interviewMode) {
        this.interviewMode = interviewMode;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }
}
