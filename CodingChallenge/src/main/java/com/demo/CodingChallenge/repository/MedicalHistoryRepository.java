package com.demo.CodingChallenge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.CodingChallenge.model.MedicalHistory;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {
    @Query("Select p from MedicalHistory p WHERE p.patient.id = ?1")
    List<MedicalHistory> getByPatientId(int patientId); 

}
