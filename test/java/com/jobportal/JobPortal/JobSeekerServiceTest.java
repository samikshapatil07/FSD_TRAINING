package com.jobportal.JobPortal;

import com.jobportal.JobPortal.model.JobSeeker;
import com.jobportal.JobPortal.model.User;
import com.jobportal.JobPortal.repository.JobSeekerRepository;
import com.jobportal.JobPortal.repository.UserRepository;
import com.jobportal.JobPortal.service.JobSeekerService;
import com.jobportal.JobPortal.service.SeekerActivityService;
import com.jobportal.JobPortal.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class JobSeekerServiceTest {

    @InjectMocks
    private JobSeekerService jobSeekerService;

    @Mock
    private JobSeekerRepository jobSeekerRepository;
    @Mock
    private UserService userService;
    @Mock
    private SeekerActivityService seekerActivityService;

    private User user;
    private JobSeeker jobSeeker;
    private JobSeeker updatedJobSeeker;
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        User user = new User();
        user.setId(1);
        user.setUsername("admin");
        user.setRole("JOB_SEEKER");

        jobSeeker = new JobSeeker();
        jobSeeker.setJobSeekerId(1);
        jobSeeker.setName("Jane Doe");
        jobSeeker.setEducation("B.Tech");
        jobSeeker.setSkills("Java, Spring");
        jobSeeker.setExperience("2 years");
        jobSeeker.setUser(user);

        updatedJobSeeker = new JobSeeker();
        updatedJobSeeker.setName("Jane Updated");
        updatedJobSeeker.setEducation("M.Tech");
        updatedJobSeeker.setSkills("Java, Spring Boot");
        updatedJobSeeker.setExperience("3 years");
    }

    @Test //<<< for register job seeker
    public void registerjobSeekerTest() {
        //expected
        when(userService.signUp(user)).thenReturn(user);
        when(jobSeekerRepository.save(jobSeeker)).thenReturn(jobSeeker);

        // actual output
        JobSeeker actual = jobSeekerService.registerjobSeeker(jobSeeker);

        assertEquals("JOB_SEEKER", actual.getUser().getRole());
        verify(userService).signUp(user);
        verify(jobSeekerRepository).save(jobSeeker);
    }

    @Test  //<<for get js by id
    public void getJobSeekerByIdTest() {
        // expected output
        when(jobSeekerRepository.findById(1)).thenReturn(Optional.of(jobSeeker));

        // actual output
        JobSeeker actual = jobSeekerService.getJobSeekerById(1);

        assertEquals("Jane Doe", actual.getName());
        verify(jobSeekerRepository).findById(1);
    }

    @Test // <<<< Update job seeker
    public void updateJobSeekerTest() {
        // expected output
        when(jobSeekerRepository.findById(1)).thenReturn(Optional.of(jobSeeker));
        when(jobSeekerRepository.save(any(JobSeeker.class))).thenReturn(jobSeeker);

        // actual output
        JobSeeker updated = jobSeekerService.updateJobSeeker(1, updatedJobSeeker);

        assertEquals("Jane Updated", updated.getName());
        assertEquals("M.Tech", updated.getEducation());
    }

    @Test // <<<< Delete job seeker
    public void deleteJobSeekerTest() {
        // expected output
        when(jobSeekerRepository.existsById(1)).thenReturn(true);

        // actual output
        jobSeekerService.deleteJobSeeker(1);

        verify(jobSeekerRepository).deleteById(1);
    }

    @Test // <<<< Get all job seekers
    public void getAllJobSeekersTest() {
        // expected output
        List<JobSeeker> expectedList = List.of(jobSeeker);
        when(jobSeekerRepository.findAll()).thenReturn(expectedList);

        // actual output
        List<JobSeeker> actualList = jobSeekerService.getAllJobSeekers();

        assertEquals(1, actualList.size());
        assertEquals("Jane Doe", actualList.get(0).getName());
        verify(jobSeekerRepository).findAll();
    }
}
