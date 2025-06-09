package com.demo.CodingChallenge.dto;

public class MedicalHistoryDto {
    private String illness;
    private int numOfYears;
    private String current_medication;

    // Getters and setters
    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public int getNumOfYears() {
        return numOfYears;
    }

    public void setNumOfYears(int numOfYears) {
        this.numOfYears = numOfYears;
    }

    public String getCurrent_medication() {
        return current_medication;
    }

    public void setCurrent_medication(String current_medication) {
        this.current_medication = current_medication;
    }
}
