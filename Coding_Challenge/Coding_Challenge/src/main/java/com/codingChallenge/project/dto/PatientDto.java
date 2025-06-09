package com.codingChallenge.project.dto;

import com.codingChallenge.project.model.MedicalHistory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PatientDto {

    private String name;
    private int age;
    private List<MedicalHistoryDto> medicalHistoryDtos;

    public List<MedicalHistoryDto> getMedicalHistoryDtos() {
        return medicalHistoryDtos;
    }

    public void setMedicalHistoryDtos(List<MedicalHistoryDto> medicalHistoryDtos) {
        this.medicalHistoryDtos = medicalHistoryDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}