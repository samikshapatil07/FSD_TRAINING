package com.demo.CodingChallenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.CodingChallenge.model.MedicalHistory;

public interface MedicalHistoryRepository  extends JpaRepository<MedicalHistory, Integer>{

}
