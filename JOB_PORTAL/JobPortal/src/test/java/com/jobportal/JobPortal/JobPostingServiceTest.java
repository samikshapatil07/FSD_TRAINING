package com.jobportal.JobPortal;

import com.jobportal.JobPortal.model.Hr;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.repository.HrRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.service.JobPostingService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JobPostingServiceTest {

    @InjectMocks
    private JobPostingService jobPostingService;

    @Mock
    private JobPostingRepository jobPostingRepository;

    @Mock
    private HrRepository hrRepository;

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
    	/*prepare the expected output*/
    	when(hrRepository.findById(1)).thenReturn(Optional.of(hr));
        when(jobPostingRepository.save(any(JobPosting.class))).thenReturn(job);

		/*actual output*/
        JobPosting savedJob = jobPostingService.postJob(job, 1);

        assertEquals(hr, savedJob.getHr());
        assertNotNull(savedJob.getCreatedAt());
        verify(hrRepository).findById(1);
        verify(jobPostingRepository).save(job);
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
    	/*prepare the expected output*/
    	List<JobPosting> expected = List.of(job);
        when(jobPostingRepository.findAll()).thenReturn(expected);

		/*actual output*/
        List<JobPosting> actual = jobPostingService.getAllJobs();

        assertEquals(expected, actual);
        verify(jobPostingRepository).findAll();
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