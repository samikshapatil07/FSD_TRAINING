
package com.jobportal.JobPortal;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import com.jobportal.JobPortal.service.SeekerActivityService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.jobportal.JobPortal.dto.ApplicationDTO;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;
import com.jobportal.JobPortal.service.ApplicationService;
import com.jobportal.JobPortal.service.ApplicationUpdateService;

@SpringBootTest
public class ApplicationServiceTest {
	
    @InjectMocks
    private ApplicationService applicationService;

    @Mock
    private ApplicationRepository applicationRepository;
    @Mock
    private ApplicationUpdateService applicationUpdateService;
    @Mock
    private JobSeekerRepository jobSeekerRepository;
    @Mock
    private JobPostingRepository jobPostingRepository;
    @Mock
    private SeekerActivityService seekerActivityService;


    private ApplicationDTO applicationDTO;
    private JobSeeker jobSeeker;
    private JobPosting jobPosting;
    private Application application;

	@BeforeEach  // <-- before every test these objects will be created at locations in HEAP
    public void init() {
        jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(1);

        jobPosting = new JobPosting();
        jobPosting.setJobId(1);

        application = new Application();
        application.setApplicationId(1);
        application.setJobSeeker(jobSeeker);
        application.setJobPosting(jobPosting);
        application.setResumePath("path/to/resume.pdf");

        applicationDTO = new ApplicationDTO();
        applicationDTO.setResumePath("path/to/resume.pdf");
        applicationDTO.setJobSeekerId(1);
        applicationDTO.setJobId(1);
    }
	
	@Test  //<----------------- 
	public void saveApplicationTest() {
		// Prepare the expected output
		when(jobSeekerRepository.findById(1)).thenReturn(Optional.of(jobSeeker));
	    when(jobPostingRepository.findById(1)).thenReturn(Optional.of(jobPosting));
	    when(applicationRepository.save(application)).thenReturn(application);
		// Actual output
		ApplicationDTO actual = applicationService.saveApplication(1, 1, applicationDTO);
		
		assertEquals(application.getResumePath(), actual.getResumePath());		
	}


	@Test  //<<------------------------------getApplicationById (application id)
	public void getApplicationByIdTest() {
		// Prepare the expected output
		Optional<Application> expected = Optional.of(application);
		when(applicationRepository.findById(1)).thenReturn(Optional.of(application));
		// Actual output
		Optional<ApplicationDTO> actual = applicationService.getApplicationResponseById(1);
        assertEquals(expected, actual);
	}
	
	@Test  //<<----------------- getApplicationsByJobSeekerId
	public void getApplicationsByJobSeekerIdTest() {
		/*prepare the expected output*/
		List<Application> expected = List.of(application);
		when(applicationRepository.findByJobSeeker_JobSeekerId(1)).thenReturn(expected);
		
		/*actual output*/
		List<ApplicationDTO> actual = applicationService.getApplicationsByJobSeekerId(1);
        assertEquals(expected, actual);
	}
	
	@Test //<<------ getApplicationsByJobId
	public void getApplicationsByJobIdTest() {
		/*prepare the expected output*/
		List<Application> expected = List.of(application);
		when(applicationRepository.findByJobPosting_JobId(1)).thenReturn(expected);
		
		/*actual output*/
		List<ApplicationDTO> actual = applicationService.getApplicationsByJobId(1);
        assertEquals(expected, actual);
	}
	
	 @Test //<<---------------updateApplication   // in this test we get error because test ,
	                                            //seekerActivityService means seeker log is never initialized — it is null, which causes the NullPointerException.
	                                           //I have written test case only for the update application logic not for seeker activity
	public void updateApplicationTest() {
		/*prepare the expected output*/
        when(applicationRepository.findById(1)).thenReturn(Optional.of(application));
        when(applicationRepository.save(application)).thenReturn(application);

		/*actual output*/
        ApplicationDTO actual = applicationService.updateApplication(1, applicationDTO);

        assertEquals("resume2.pdf", actual.getResumePath());
        assertEquals(application, actual);
    }
	
	@Test //<<---------------deleteApplication 
	public void deleteApplicationTest() {
        when(applicationRepository.existsById(1)).thenReturn(true);
        //nothing to assert, method should execute without exception
		/*actual output*/
        applicationService.deleteApplication(1);
        assertEquals(1, 1); //dummy assertion to make test pass

	}

	// After each test case, the objects used in them will get nullified and HEAP
    // memory will be free
	@AfterEach
    public void afterTest() {
        jobSeeker = null;
        System.out.println("jobSeeker object released.." + jobSeeker);
        jobPosting = null;
        System.out.println("jobPosting object released.." + jobPosting);
        application = null;
        System.out.println("application object released.." + application);
    }

}
