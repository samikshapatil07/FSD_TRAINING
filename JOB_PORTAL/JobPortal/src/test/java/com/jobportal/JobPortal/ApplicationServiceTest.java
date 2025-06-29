package com.jobportal.JobPortal;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.List;

import com.jobportal.JobPortal.dto.ApplicationDTO;
import com.jobportal.JobPortal.exception.ResourceNotFoundException;
import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Application.Status;
import com.jobportal.JobPortal.model.JobPosting;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.JobPostingRepository;
import com.jobportal.JobPortal.repository.JobSeekerRepository;
import com.jobportal.JobPortal.service.ApplicationService;
import com.jobportal.JobPortal.service.ApplicationUpdateService;
import com.jobportal.JobPortal.service.SeekerActivityService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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

    private Application application;
    private JobSeeker jobSeeker;
    private JobPosting jobPosting;
    private ApplicationDTO applicationDTO;

    @BeforeEach
    public void init() {
        jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(1);

        jobPosting = new JobPosting();
        jobPosting.setJobId(1);

        application = new Application();
        application.setApplicationId(1);
        application.setJobSeeker(jobSeeker);
        application.setJobPosting(jobPosting);
        application.setResumePath("resume1.pdf");
        application.setStatus(Status.APPLIED);

        applicationDTO = new ApplicationDTO();
        applicationDTO.setApplicationId(1);
        applicationDTO.setJobSeekerId(1);
        applicationDTO.setJobId(1);
        applicationDTO.setResumePath("resume1.pdf");
    }

  
    @Test //Test for fetching application by ID
    public void testGetApplicationById() {
        when(applicationRepository.findById(1)).thenReturn(Optional.of(application));

        ApplicationDTO result = applicationService.getApplicationById(1);
        assertEquals("resume1.pdf", result.getResumePath());
        assertEquals(1, result.getJobId());
    }



    @Test //Test for deleting an application with status = APPLIED
    public void testDeleteApplicationSuccess() {
        when(applicationRepository.findById(1)).thenReturn(Optional.of(application));

        applicationService.deleteApplication(1);

        verify(applicationRepository, times(1)).delete(application);
    }


    @Test //Test for getting applications for job seeker
    public void testGetApplicationsForJs() {
        List<Application> expected = List.of(application);

        when(applicationRepository.getApplicationsForJs("user1")).thenReturn(expected);

        List<Application> result = applicationService.getApplicationsForJs("user1");

        assertEquals(1, result.size());
        assertEquals(application, result.get(0));
    }


    @Test //Test for getting applications for HR
    public void testGetApplicationsForHr() {
        List<Application> expected = List.of(application);

        when(applicationRepository.getApplicationsForHr("hr1")).thenReturn(expected);

        List<Application> result = applicationService.getApplicationsForHr("hr1");

        assertEquals(1, result.size());
        assertEquals(application, result.get(0));
    }


    @Test
    public void testGetApplicationByIdNotFound() {
        when(applicationRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> applicationService.getApplicationById(1));
    }


    @Test // test for invalid ID in updateApplicationStatus
    public void testUpdateApplicationStatusNotFound() {
        when(applicationRepository.findById(999)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> applicationService.updateApplicationStatus(99, Status.REJECTED));

        assertEquals("Application not found", ex.getMessage());
    }

    @AfterEach
    public void tearDown() {
        jobSeeker = null;
        jobPosting = null;
        application = null;
        applicationDTO = null;
    }
}
