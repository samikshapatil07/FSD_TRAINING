package com.codingChallenge.project.controller;

import com.codingChallenge.project.model.Patient;
import com.codingChallenge.project.repository.PatientDoctorRepository;
import com.codingChallenge.project.repository.PatientRepository;
import com.codingChallenge.project.service.PatientDoctorService;
import com.codingChallenge.project.service.PatientService;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientDoctorControllerTest {

    @InjectMocks
    private PatientDoctorService patientDoctorService;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private PatientDoctorRepository patientDoctorRepository;

    private Patient patient;

    @BeforeEach
    public void init(){
        patient = new Patient();
        patient.setName("Patient");
        patient.setAge(20);
    }

    @Test
    public void getPatientsByDoctorTest(){
        List<Patient> expected = List.of(patient);

        when(patientRepository.findById(1)).thenReturn(Optional.of(patient));
        when(patientDoctorRepository.getPatientsByDoctor(1)).thenReturn(expected);

        List<Patient> actual = patientDoctorService.getPatientsByDoctor(1);

        assertEquals(expected, actual);

    }

    @AfterEach
    public void makeNull(){
        patient = null;
    }


}