package com.jobportal.JobPortal;

import com.jobportal.JobPortal.dto.InterviewDTO;
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

    @Test // << getInterviewById test
    public void testGetInterviewById() {
        when(interviewRepository.findById(1)).thenReturn(Optional.of(interview));

        Optional<Interview> actual = interviewService.getInterviewById(1);

        assertTrue(actual.isPresent());
        assertEquals(1, actual.get().getInterviewId());
        verify(interviewRepository).findById(1);
    }

    @Test // << getInterviewsByApplicationId test
    public void testGetInterviewsByApplicationId() {
        when(interviewRepository.findByApplicationApplicationId(1)).thenReturn(List.of(interview));

        List<InterviewDTO> actual = interviewService.getInterviewsByApplicationId(1);

        assertEquals(1, actual.size());
        assertEquals("Zoom", actual.get(0).getInterviewLocation());
        verify(interviewRepository).findByApplicationApplicationId(1);
    }

    @Test // << updateInterview test
    public void testUpdateInterview() {
        when(interviewRepository.findById(1)).thenReturn(Optional.of(interview));
        when(applicationRepository.findById(1)).thenReturn(Optional.of(app));
        when(interviewRepository.save(any(Interview.class))).thenReturn(interview);

        Interview input = new Interview();
        input.setInterviewDate(LocalDate.now().plusDays(1));
        input.setInterviewLocation("Office");
        input.setInterviewMode(Interview.InterviewMode.OFFLINE);

        Interview updated = interviewService.updateInterview(input, 1);

        assertEquals("Office", updated.getInterviewLocation());
        assertEquals(Interview.InterviewMode.OFFLINE, updated.getInterviewMode());
    }


    
    // After each test case, the objects used in them will get nullified and HEAP
    // memory will be free
    @AfterEach
    public void afterTest () {
        app = null;
        System.out.println("jobSeeker object released.." + app);
        interview = null;
        System.out.println("jobPosting object released.." + interview);

    }
}