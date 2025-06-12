package com.demo.CodingChallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.model.PatientDoctor;

@Repository
public interface PatientDoctorRepository extends JpaRepository<PatientDoctor, Integer> {
    @Query("Select p.patient from PatientDoctor p WHERE p.doctor.id = ?1")
    List<Patient> getPatientsByDoctor(int doctorId);
}