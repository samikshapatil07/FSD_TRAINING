package com.jobportal.JobPortal;

import com.jobportal.JobPortal.dto.JobPostingDTO;
import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.HrRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.service.JobPostingService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class JobPostingServiceTest {

    @InjectMocks
    private JobPostingService jobPostingService;

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private HrRepository hrRepository;
    
    @Mock
    private ApplicationRepository applicationRepository;


    private Hr hr;
    private JobPosting job;
    private JobPosting updatedJob;

    @BeforeEach
    public void setUp() {
        hr = new Hr();
        hr.setId(1);
        hr.setName("John Doe");
        hr.setCompanyName("ABC Corp");

        job = new JobPosting();
        job.setJobId(1);
        job.setJobTitle("Java Developer");
        job.setDescription("Experienced in Spring Boot");
        job.setLocation("Bangalore");
        job.setCompany("ABC Corp");
        job.setHr(hr);
        job.setCreatedAt(LocalDateTime.now());

        updatedJob = new JobPosting();
        updatedJob.setJobTitle("Senior Java Developer");
        updatedJob.setDescription("Spring Boot, Microservices");
        updatedJob.setLocation("Remote");
        updatedJob.setCompany("XYZ Ltd");
        updatedJob.setSkills("Java, Spring");
        updatedJob.setExperience("5+ years");
        updatedJob.setDepartment("Engineering");
        updatedJob.setSalary(1200000);
    }

    @Test // <<<< update job test
    public void updateJobTest() {
    	/*prepare the expected output*/
    	when(jobPostingRepository.findById(1)).thenReturn(Optional.of(job));
        when(jobPostingRepository.save(any(JobPosting.class))).thenReturn(job);

		/*actual output*/
        JobPosting result = jobPostingService.updateJob(1, updatedJob, hr);

        assertEquals("Senior Java Developer", result.getJobTitle());
        assertEquals("Remote", result.getLocation());
        assertEquals("XYZ Ltd", result.getCompany());
    }
    
    @Test // <<<< delete job test
    public void deleteJobTest() {
    	/*prepare the expected output*/
    	when(jobPostingRepository.existsById(1)).thenReturn(true);

		/*actual output*/
        jobPostingService.deleteJob(1);
        verify(jobPostingRepository).deleteById(1);
    }
    
    @Test // <<<< get all jobs test
    public void getAllJobsTest() {
        // Prepare mock job list
        List<JobPosting> jobList = List.of(job);
        when(jobPostingRepository.findAll()).thenReturn(jobList);

        // Actual call to service
        List<JobPostingDTO> actualList = jobPostingService.getAllJobs();

        // Assertions
        assertEquals(1, actualList.size());
        assertEquals("Java Developer", actualList.get(0).getJobTitle());
        assertEquals("Bangalore", actualList.get(0).getLocation());
        assertEquals("ABC Corp", actualList.get(0).getCompany());

        verify(jobPostingRepository).findAll();
    }
    
    @Test // <<<< get job by id test
    public void getJobByIdTest() {
        when(jobPostingRepository.findById(1)).thenReturn(Optional.of(job));

        JobPostingDTO actual = jobPostingService.getJobById(1);

        assertEquals(job.getJobId(), actual.getJobId());
        assertEquals(job.getJobTitle(), actual.getJobTitle());
    }

    @Test // <<<< search jobs test
    public void searchJobsTest() {
        List<JobPosting> entityList = List.of(job);
        when(jobPostingRepository.searchJobs("Java Developer", "Bangalore", "ABC Corp"))
            .thenReturn(entityList);

        List<JobPostingDTO> actual = jobPostingService.searchJobs("Java Developer", "Bangalore", "ABC Corp");

        assertEquals(1, actual.size());
        assertEquals(job.getJobTitle(), actual.get(0).getJobTitle());
    }
    
 // After each test case, the objects used in them will get nullified and HEAP
    // memory will be free
	@AfterEach
    public void afterTest() {
        job = null;
        System.out.println("job object released.." + job);
        hr = null;
        System.out.println("jobPosting object released.." + hr);
        updatedJob = null;
        System.out.println("application object released.." + updatedJob);
    }
}