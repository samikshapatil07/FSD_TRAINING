package com.demo.CodingChallenge.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medical_history")

public class MedicalHistory { //ml in repo.

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
     
	private String illness;
    private int numOfYears;
    private String current_medication;
    
  // medical_history: patient = M:1
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	public int getNum_of_years() {
		return numOfYears;
	}

	public void setNum_of_years(int numOfYears) {
		this.numOfYears = numOfYears;
	}

	public String getCurrent_medication() {
		return current_medication;
	}

	public void setCurrent_medication(String current_medication) {
		this.current_medication = current_medication;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
    
}
