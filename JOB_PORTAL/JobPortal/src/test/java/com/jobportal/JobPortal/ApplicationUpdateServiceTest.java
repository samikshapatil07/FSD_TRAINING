package com.jobportal.JobPortal;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.ApplicationUpdate;
import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.repository.ApplicationUpdateRepository;
import com.jobportal.JobPortal.service.ApplicationUpdateService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ApplicationUpdateServiceTest {

    @InjectMocks
    private ApplicationUpdateService service;

    @Mock
    private ApplicationUpdateRepository repository;

    private Application application;
    private JobSeeker jobSeeker;
    private ApplicationUpdate update;

    @BeforeEach // <-- creates objects before each test
    public void init() {
        application = new Application();
        application.setApplicationId(1);

        jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(1);

        update = new ApplicationUpdate();
        update.setUpdateId(1);
        update.setApplication(application);
        update.setJobSeeker(jobSeeker);
        update.setUpdatedResumePath("resume/path.pdf");
    }

    @Test //<<<<< createUpdate test
    public void testCreateUpdate() {
		/*prepare the expected output*/
        when(repository.save(update)).thenReturn(update);

		/*actual output*/
        ApplicationUpdate saved = service.createUpdate(update);

        assertEquals(update, saved);
        verify(repository).save(update);
    }

    @Test //<<<<< get updates by applicationId
    public void testGetUpdatesByApplicationId() {
		/*prepare the expected output*/
        List<ApplicationUpdate> expectedUpdates = Arrays.asList(update);
        when(repository.findByApplicationApplicationId(1)).thenReturn(expectedUpdates);

		/*actual output*/
        List<ApplicationUpdate> actualUpdates = service.getUpdatesByApplicationId(1);

        assertEquals(expectedUpdates, actualUpdates);
        verify(repository).findByApplicationApplicationId(1);
    }

    @Test //<<<<< get updates by jobSeekerId
    public void testGetUpdatesByJobSeekerId() {
		/*prepare the expected output*/
        List<ApplicationUpdate> expectedUpdates = Arrays.asList(update);
        when(repository.findByJobSeekerJobSeekerId(1)).thenReturn(expectedUpdates);

		/*actual output*/
        List<ApplicationUpdate> actualUpdates = service.getUpdatesByJobSeekerId(1);

        assertEquals(expectedUpdates, actualUpdates);
        verify(repository).findByJobSeekerJobSeekerId(1);
    }

    @Test //<<<<< record resume update test
    public void testRecordResumeUpdate() {
		/*prepare the expected output*/
        doAnswer(invocation -> {
            ApplicationUpdate savedUpdate = invocation.getArgument(0);
            assertEquals("resume/path.pdf", savedUpdate.getUpdatedResumePath());
            assertEquals(application, savedUpdate.getApplication());
            assertEquals(jobSeeker, savedUpdate.getJobSeeker());
            return null;
        }).when(repository).save(any(ApplicationUpdate.class));

		/*actual output*/
        service.recordResumeUpdate(application, jobSeeker, "resume/path.pdf");

        verify(repository, times(1)).save(any(ApplicationUpdate.class));
    }

        // After each test case, the objects used in them will get nullified and HEAP
        // memory will be free
        @AfterEach
        public void afterTest () {
            jobSeeker = null;
            System.out.println("jobSeeker object released.." + jobSeeker);
            application = null;
            System.out.println("jobPosting object released.." + application);
            update = null;
            System.out.println("application object released.." + update);
        }
    }