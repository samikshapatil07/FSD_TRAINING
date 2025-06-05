package com.jobportal.JobPortal;

import com.jobportal.JobPortal.model.Application;
import com.jobportal.JobPortal.model.Interview;
import com.jobportal.JobPortal.model.Interview.InterviewOutcome;
import com.jobportal.JobPortal.model.Application.Status;
import com.jobportal.JobPortal.repository.ApplicationRepository;
import com.jobportal.JobPortal.repository.InterviewRepository;
import com.jobportal.JobPortal.service.InterviewService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class InterviewServiceTest {

    @InjectMocks
    private InterviewService interviewService;

    @Mock
    private InterviewRepository interviewRepository;
    @Mock
    private ApplicationRepository applicationRepository;

    private Application app;
    private Interview interview;

    @BeforeEach // <-- setup test data before each test
    public void init() {
        app = new Application();
        app.setApplicationId(1);
        app.setStatus(Status.APPLIED);

        interview = new Interview();
        interview.setInterviewId(1);
        interview.setInterviewDate(LocalDate.from(LocalDateTime.now()));
        interview.setInterviewLocation("Zoom");
        interview.setInterviewMode(Interview.InterviewMode.valueOf("ONLINE"));
        interview.setApplication(app);
        interview.setOutcome(InterviewOutcome.PENDING);
    }

    @Test // << scheduleInterview test
    public void testScheduleInterview() {
        // expected output
        when(applicationRepository.findById(1)).thenReturn(Optional.of(app));
        when(interviewRepository.save(interview)).thenReturn(interview);
        when(applicationRepository.save(any(Application.class))).thenReturn(app);

        // actual output
        Interview scheduled = interviewService.scheduleInterview(1, interview);

        assertEquals(app, scheduled.getApplication());
        assertEquals(Status.INTERVIEW_SCHEDULED, app.getStatus());
        verify(interviewRepository).save(interview);
        verify(applicationRepository).save(app);
    }

    @Test // << updateInterview test
    public void testUpdateInterview() {
        // expected output
        when(interviewRepository.findById(1)).thenReturn(Optional.of(interview));
        when(applicationRepository.findById(1)).thenReturn(Optional.of(app));
        when(interviewRepository.save(any(Interview.class))).thenReturn(interview);

        Interview input = new Interview();
        input.setInterviewId(1);
        input.setInterviewDate(LocalDate.from(LocalDateTime.now().plusDays(1)));
        input.setInterviewLocation("Office");
        input.setInterviewMode(Interview.InterviewMode.valueOf("OFFLINE"));

        // actual output
        Interview updated = interviewService.updateInterview(input, 1);

        assertEquals("Office", updated.getInterviewLocation());
        assertEquals("OFFLINE", updated.getInterviewMode());
        assertEquals(InterviewOutcome.PENDING, updated.getOutcome());
        assertEquals(app, updated.getApplication());
    }

    @Test // << updateInterviewStatus test
    public void testUpdateInterviewStatus() {
        // expected output
        interview.setOutcome(InterviewOutcome.PENDING);
        app.setStatus(Status.INTERVIEW_SCHEDULED);
        when(interviewRepository.findById(1)).thenReturn(Optional.of(interview));
        when(interviewRepository.save(any(Interview.class))).thenReturn(interview);
        when(applicationRepository.save(any(Application.class))).thenReturn(app);

        // actual output
        Interview updated = interviewService.updateInterviewStatus(1, InterviewOutcome.INTERVIEW_COMPLETED_REJECTED);

        assertEquals(InterviewOutcome.INTERVIEW_COMPLETED_REJECTED, updated.getOutcome());
        assertEquals(Status.INTERVIEW_COMPLETED_REJECTED, app.getStatus());
    }

    @Test // << getInterviewsByApplicationId test
    public void testGetInterviewsByApplicationId() {
        // expected output
        List<Interview> expected = Arrays.asList(interview);
        when(interviewRepository.findByApplicationApplicationId(1)).thenReturn(expected);

        // actual output
        List<Interview> actual = interviewService.getInterviewsByApplicationId(1);

        assertEquals(expected, actual);
        verify(interviewRepository).findByApplicationApplicationId(1);
    }

    @Test // << getInterviewById test
    public void testGetInterviewById() {
        // expected output
        when(interviewRepository.findById(1)).thenReturn(Optional.of(interview));

        // actual output
        Interview actual = interviewService.getInterviewById(1);

        assertEquals(interview, actual);
    }

    @Test // << deleteInterview test
    public void testDeleteInterview() {
        // no return value to assert; just verify method call
        interviewService.deleteInterview(1);
        verify(interviewRepository).deleteById(1);
    }

    // After each test case, the objects used in them will get nullified and HEAP
    // memory will be free
    @AfterEach
    public void afterTest () {
        user = null;
        System.out.println("jobSeeker object released.." + user);
        hr = null;
        System.out.println("jobPosting object released.." + hr);
        updatedHr = null;
        System.out.println("application object released.." + updatedHr);
    }
}
