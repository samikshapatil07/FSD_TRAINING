package com.codingChallenge.project.repository;

import com.codingChallenge.project.model.Patient;
import com.codingChallenge.project.model.PatientDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer> {
    @Query("Select p.patient from PatientDoctor p WHERE p.doctor.id = ?1")
    List<Patient> getPatientsByDoctor(int doctorId);


}
