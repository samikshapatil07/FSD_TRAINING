//package com.jobportal.JobPortal.service;
//
//import com.jobportal.JobPortal.exception.ResourceNotFoundException;
//import com.jobportal.JobPortal.model.Hr;
//import com.jobportal.JobPortal.model.JobPosting;
//import com.jobportal.JobPortal.repository.HrRepository;
//import com.jobportal.JobPortal.repository.JobPostingRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class JobPostingService {
//
//    @Autowired
//    private JobPostingRepository jobPostingRepository;
//    
//    @Autowired
//    private HrRepository hrRepository;
//
//    
//  //--------------------- Posts a new Job ----------------------------
//    public JobPosting postJob(JobPosting jobPosting, Long hrId) {
//        Hr hr = hrRepository.findById(hrId)
//                .orElseThrow(() -> new ResourceNotFoundException("HR not found with ID: " + hrId));
//        jobPosting.setHr(hr);
//        jobPosting.setCreatedAt(LocalDateTime.now());
//        return jobPostingRepository.save(jobPosting);
//    }
//  //--------------------- Get all Job Postings ------------------
//    public List<JobPosting> getAllJobs() {
//        return jobPostingRepository.findAll();
//    }
//
//  //--------------------- Get a Job by ID --------------------
//    public JobPosting getJobById(Long jobId) {
//        return jobPostingRepository.findById(jobId)
//                .orElseThrow(() -> new ResourceNotFoundException("Job posting not found with ID: " + jobId));
//    }
//
//   //--------------------- Get Jobs  by Location ----------
//    public List<JobPosting> getJobsByLocation(String location) {
//        return jobPostingRepository.findByLocationContainingIgnoreCase(location);
//    }
//
//    //--------------------- Get Jobs by Skills ------------
//    public List<JobPosting> getJobsBySkills(String skills) {
//        return jobPostingRepository.findBySkillsContainingIgnoreCase(skills);
//    }
//
//    //--------------------- Get Jobs by Company -----------
//    public List<JobPosting> getJobsByCompany(String company) {
//        return jobPostingRepository.findByCompanyContainingIgnoreCase(company);
//    }
//    
//    //--------------------- Deletes a Job by its ID -----------------------
//    public void deleteJob(Long jobId) {
//        if (!jobPostingRepository.existsById(jobId)) {
//            throw new ResourceNotFoundException("Job posting not found with ID: " + jobId);
//        }
//        jobPostingRepository.deleteById(jobId);
//    }
//    
//    //--------------------- update a Job by its ID -----------------------
//
//    public JobPosting updateJob(Long id, JobPosting updatedJob) {
//        JobPosting existingJob = jobPostingRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Job not found with id: " + id));
//
//        existingJob.setJobTitle(updatedJob.getJobTitle());
//        existingJob.setDescription(updatedJob.getDescription());
//        existingJob.setSkills(updatedJob.getSkills());
//        existingJob.setLocation(updatedJob.getLocation());
//        existingJob.setSalary(updatedJob.getSalary());
//        existingJob.setDepartment(updatedJob.getDepartment());
//        existingJob.setCompany(updatedJob.getCompany());
//        existingJob.setExperience(updatedJob.getExperience());
//
//        return jobPostingRepository.save(existingJob);
//    }
//}
