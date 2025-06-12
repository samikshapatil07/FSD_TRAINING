package com.jobportal.JobPortal;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    
    @Test // <<<< post job test
    public void postJobTest() {
        List<JobPosting> jobList = List.of(job); // single job into a List

    	/*prepare the expected output*/
        when(hrRepository.findById(1)).thenReturn(Optional.of(hr));
        when(jobPostingRepository.saveAll(any())).thenReturn(jobList);

        //Call the method
        List<JobPosting> savedJobs = jobPostingService.batchPostJobs(jobList, 1);

		/*actual output*/
        assertEquals(1, savedJobs.size());
        assertEquals(hr, savedJobs.get(0).getHr());
        assertNotNull(savedJobs.get(0).getCreatedAt());
        verify(hrRepository).findById(1);
        verify(jobPostingRepository).saveAll(any());
    }

    @Test // <<<< update job test
    public void updateJobTest() {
    	/*prepare the expected output*/
    	when(jobPostingRepository.findById(1)).thenReturn(Optional.of(job));
        when(jobPostingRepository.save(any(JobPosting.class))).thenReturn(job);

		/*actual output*/
        JobPosting result = jobPostingService.updateJob(1, updatedJob);

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
        List<JobPosting> jobList = List.of(job);
        Page<JobPosting> expectedPage = new PageImpl<>(jobList);

        when(jobPostingRepository.findAll(PageRequest.of(0, 5))).thenReturn(expectedPage);

        Page<JobPosting> actualPage = jobPostingService.getAllJobs(0, 5);

        assertEquals(expectedPage, actualPage);
        verify(jobPostingRepository).findAll(PageRequest.of(0, 5));
    }
    
    @Test // <<<< get job by id test
    public void getJobByIdTest() {
    	/*prepare the expected output*/
    	when(jobPostingRepository.findById(1)).thenReturn(Optional.of(job));

		/*actual output*/
        JobPosting actual = jobPostingService.getJobById(1);

        assertEquals(job, actual);
    }

    @Test // <<<< search jobs test
    public void searchJobsTest() {
    	/*prepare the expected output*/     
    	List<JobPosting> expected = List.of(job);
        when(jobPostingRepository.searchJobs("Java Developer", "Bangalore", "ABC Corp")).thenReturn(expected);

		/*actual output*/
        List<JobPosting> actual = jobPostingService.searchJobs("Java Developer", "Bangalore", "ABC Corp");

        assertEquals(expected, actual);
    }
 // After each test case, the objects used in them will get nullified and HEAP
    // memory will be free
	@AfterEach
    public void afterTest() {
        job = null;
        System.out.println("jobSeeker object released.." + job);
        hr = null;
        System.out.println("jobPosting object released.." + hr);
        updatedJob = null;
        System.out.println("application object released.." + updatedJob);
    }
}