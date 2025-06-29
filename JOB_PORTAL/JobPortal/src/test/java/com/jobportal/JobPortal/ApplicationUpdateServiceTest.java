package com.jobportal.JobPortal;

import com.jobportal.JobPortal.dto.ApplicationUpdateDTO;
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

import java.sql.Timestamp;
import java.time.Instant;
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
        
        update.setUpdatedOn(Timestamp.from(Instant.now()));
    }

    @Test //<<<<< get updates by applicationId
    public void testGetUpdatesByApplicationId() {
        List<ApplicationUpdate> expected = Arrays.asList(update);
        when(repository.get_updates_by_app(1)).thenReturn(expected);

        List<ApplicationUpdateDTO> actual = service.getUpdatesByApplicationId(1);

        assertEquals(1, actual.size());
        assertEquals(update.getUpdateId(), actual.get(0).getUpdateId());
        assertEquals(update.getUpdatedResumePath(), actual.get(0).getUpdatedResumePath());
        assertEquals(update.getApplication().getApplicationId(), actual.get(0).getApplicationId());
        assertEquals(update.getJobSeeker().getJobSeekerId(), actual.get(0).getJobSeekerId());

        verify(repository).get_updates_by_app(1);
    }


    @Test //<<<<< get updates by jobSeekerId
    public void testGetUpdatesByJobSeekerId() {
        List<ApplicationUpdate> expected = Arrays.asList(update);
        when(repository.get_updates_by_js(1)).thenReturn(expected);

        List<ApplicationUpdateDTO> actual = service.getUpdatesByJobSeekerId(1);

        assertEquals(1, actual.size());
        assertEquals(update.getUpdateId(), actual.get(0).getUpdateId());
        assertEquals(update.getUpdatedResumePath(), actual.get(0).getUpdatedResumePath());
        assertEquals(update.getApplication().getApplicationId(), actual.get(0).getApplicationId());
        assertEquals(update.getJobSeeker().getJobSeekerId(), actual.get(0).getJobSeekerId());

        verify(repository).get_updates_by_js(1);
    }
    
    @Test // <<<<< No updates exist for given application ID
    public void testGetUpdatesByApplicationId_NotFound() {
        when(repository.get_updates_by_app(999)).thenReturn(List.of());

        List<ApplicationUpdateDTO> result = service.getUpdatesByApplicationId(999);

        // Expecting empty list
        assertEquals(0, result.size());
        verify(repository).get_updates_by_app(999);
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