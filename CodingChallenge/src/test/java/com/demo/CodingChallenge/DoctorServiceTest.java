package com.demo.CodingChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.CodingChallenge.model.Doctor;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.repository.DoctorRepository;
import com.demo.CodingChallenge.service.DoctorService;

@SpringBootTest
public class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorRepository doctorRepository;

    private Doctor doctor;
    private Patient patient1;
    private Patient patient2;
    private List<Patient> patientList;

    @BeforeEach
    public void init() {
        doctor = new Doctor();
        doctor.setId(1);
        doctor.setName("Dr. John");
      //  doctor.setSpeciality(Doctor.Speciality.PHYSICIAN);

        patient1 = new Patient();
        patient1.setId(1);
        patient1.setName("Harry Patient");
        patient1.setAge(30);

        patient2 = new Patient();
        patient2.setId(2);
        patient2.setName("Alice Patient");
        patient2.setAge(40);

        patientList = Arrays.asList(patient1, patient2);

       // doctor.setPatients(patientList);
    }

    @Test
    public void getPatientsByDoctorIdTest() {
        //when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor));

      //  List<Patient> result = doctorService.getPatientsByDoctorId(1);

//        assertNotNull(result);
//        assertEquals(2, result.size());
//        assertEquals("Harry Patient", result.get(0).getName());
//        assertEquals("Alice Patient", result.get(1).getName());
   }

    @Test
    public void getPatientsByDoctorId_DoctorNotFound() {
        when(doctorRepository.findById(99)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
      //      doctorService.getPatientsByDoctorId(999);
        });

        assertEquals("Doctor not found", exception.getMessage());
    }

    @AfterEach
    public void tearDown() {
        doctor = null;
        patient1 = null;
        patient2 = null;
        patientList = null;

        System.out.println("Objects released...");
    }
}
