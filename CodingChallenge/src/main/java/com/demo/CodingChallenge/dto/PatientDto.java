package com.demo.CodingChallenge.dto;

import java.util.List;

public class PatientDto {
    private int id;
    private String name;
    private int age;
    private List<MedicalHistoryDto> medicalHistories;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<MedicalHistoryDto> getMedicalHistories() {
        return medicalHistories;
    }

    public void setMedicalHistories(List<MedicalHistoryDto> medicalHistories) {
        this.medicalHistories = medicalHistories;
    }
}
