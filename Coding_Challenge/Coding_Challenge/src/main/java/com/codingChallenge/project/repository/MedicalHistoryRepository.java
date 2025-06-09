package com.codingChallenge.project.repository;

import com.codingChallenge.project.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Integer> {
    @Query("Select p from MedicalHistory p WHERE p.patient.id = ?1")
    List<MedicalHistory> getByPatientId(int patientId);
}

