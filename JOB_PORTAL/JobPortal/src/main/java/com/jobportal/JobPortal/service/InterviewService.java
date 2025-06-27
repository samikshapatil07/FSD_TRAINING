package com.jobportal.JobPortal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.dto.InterviewDTO;
import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.InterviewRepository;

@Service
public class InterviewService {

    @Autowired
    private InterviewRepository repository;

    @Autowired
    private ApplicationRepository applicationRepository;
    
    @Autowired
    private InterviewRepository interviewRepository;
    
//    @Autowired
//    private HrRepository hrRepository;

    
    
    //------------------------- schedule interview for application (HR)----------------------------------------
    public Interview scheduleInterview(int appId, Interview interview,String username) {
    	//get the application using id
        Application application = applicationRepository.findById(appId)
                .orElseThrow(() -> new RuntimeException("Application not found with ID: " + appId));

        
//        // Get HR
//        Hr hr = hrRepository.getHrByUsername(username);
//        if (hr == null) {
//            throw new RuntimeException("HR not found with username: " + username);
//        }
        
        // Attach application to interview
        interview.setApplication(application);
        // Save interview
        Interview saved = repository.save(interview);
        // Update application status
        application.setStatus(Application.Status.INTERVIEW_SCHEDULED);
        applicationRepository.save(application);

        return saved;
    }
  
    
    //----------------------------- Updates interview details(Hr) ------------------------------------------
    public Interview updateInterview(Interview interview, int interviewId) {
    	//get interview by id
    	Interview existing = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found with ID: " + interviewId));

    	//update fields if they are not null
        if (interview.getInterviewId() != null)
        	existing.setInterviewId(interview.getInterviewId());
		if (interview.getInterviewDate() != null)
			existing.setInterviewDate(interview.getInterviewDate());
		if (interview.getInterviewLocation() != null)
			existing.setInterviewLocation(interview.getInterviewLocation());
		if (interview.getInterviewMode() != null)
			existing.setInterviewMode(interview.getInterviewMode());
		if (interview.getOutcome() != null)
			existing.setOutcome(interview.getOutcome());

		//save the updated imterview
        return interviewRepository.save(existing);
    }
//------------------------ Update outcome of an interview (hr) ---------------------------    
    public InterviewDTO updateInterviewOutcome(int interviewId, Interview.InterviewOutcome outcome) {

    	//get interview by id
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));
        // Set new outcome as PASSED / FAILED / PENDING to intervuew
        interview.setOutcome(outcome);
        //save the updated interview obj. to get new outcome
        Interview updated = interviewRepository.save(interview);
        // Fetch application
        Application application = interview.getApplication();

        // Update application status based on outcome
        switch (outcome) {
            case PASSED -> application.setStatus(Application.Status.OFFERED);
            case FAILED -> application.setStatus(Application.Status.REJECTED);
            case PENDING -> application.setStatus(Application.Status.INTERVIEW_SCHEDULED);
        }
        //save updated applicationn with new status
        applicationRepository.save(application);

        return InterviewDTO.converttoDto(updated);    }
    
    //-------------------- delete interview -----------------------
    public void deleteInterview(Integer interviewId) {
        Interview interview = interviewRepository.findById(interviewId)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        interview.setApplication(null); 

        interviewRepository.delete(interview);
    }
    
    //-------------------- get interview by application id -----------------------
    public List<InterviewDTO> getInterviewsByApplicationId(Integer appId) {
        List<Interview> interviews = interviewRepository.findByApplicationApplicationId(appId);
        return interviews.stream().map(InterviewDTO::converttoDto).toList();
    }
    //-------------------- get interview for logged in hr-----------------------

	public List<InterviewDTO> getInterviewsByHRUsername(String username) {
	    List<Interview> interviews = interviewRepository.findAllByHrUsername(username);
		// TODO Auto-generated method stub
	    return InterviewDTO.converttoDto(interviews);
	}
    //-------------------- get interviewfor logged in js -----------------------

	public List<InterviewDTO> getInterviewsForJS(String username) {
	    List<Interview> interviews = interviewRepository.findAllByJsUsername(username);
		// TODO Auto-generated method stub
	    return InterviewDTO.converttoDto(interviews);
	}
	
	
	
	
	
//===========================================================================
	   
    //-------------------- get interview by interview  id -----------------------
    public Optional<Interview> getInterviewById(Integer interviewId) {
        return interviewRepository.findById(interviewId);
    }
	

	public List<InterviewDTO> getAllInterviews(Integer page, Integer size) {
		 /** Activate Pageable Interface */
        Pageable pageable = PageRequest.of(page, size);
        /** Call findAll inbuilt method as pass this pageable interface ref */
        List<Interview> interview = interviewRepository.findAll(pageable).getContent();
        return InterviewDTO.converttoDto(interview);
    }
}
