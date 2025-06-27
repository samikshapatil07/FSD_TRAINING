package com.jobportal.JobPortal.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobportal.JobPortal.dto.ApplicationUpdateDTO;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.ApplicationUpdate;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.repository.ApplicationUpdateRepository;

@Service
public class ApplicationUpdateService {

    @Autowired
    private ApplicationUpdateRepository repository;
        
////---------------------- Saves a new application update ------------------------------------
//    /*// ssaves a new application update record into the database*/
//    public ApplicationUpdate createUpdate(ApplicationUpdate update) {
//        return repository.save(update);
//    }
    
//-------------------- get application update by app. id -----------------------
    public List<ApplicationUpdateDTO> getUpdatesByApplicationId(int applicationId) {
    	 List<ApplicationUpdate> applicationUpdate = repository.get_updates_by_app(applicationId);
         return ApplicationUpdateDTO.converttoDto(applicationUpdate);
         }
    
//----------------------------- Records a resume update made by a job seeker for a specific application ----------------
    /*//records a resume update made by the job seeker for a specific application
*this we have used in applicaction servie*/
    public void recordResumeUpdate(Application app, JobSeeker jobSeeker, String resumePath) {
        //create a new application obj.
    	ApplicationUpdate update = new ApplicationUpdate();
    	//set the referance to app. and js
        update.setApplication(app);
        update.setJobSeeker(jobSeeker);
        //set the path to new updated resume
        update.setUpdatedResumePath(resumePath);
        //set the currebnt time stamp
        update.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
        //save update to db
        repository.save(update);
    }

    
    
    
    
    
    
//====================================================================================================
  //--------------------get application update by job seeker id
    public List<ApplicationUpdateDTO> getUpdatesByJobSeekerId(int jobSeekerId) {
   	 List<ApplicationUpdate> application = repository.get_updates_by_js(jobSeekerId);
     return ApplicationUpdateDTO.converttoDto(application);
     }
}
