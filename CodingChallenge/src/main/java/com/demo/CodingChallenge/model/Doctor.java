package com.demo.CodingChallenge.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor { //d in repo.
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private Speciality  speciality;
    
    //doctor : user = 1:1
    @OneToOne
	@JoinColumn(name = "user_id")
    private User user; 
    
    //doctor : patient =M:N
    @JoinTable(name = "patient_doctor")
    private List<Patient> patients;
    
    
    public enum Speciality {
        PHYSICIAN,
        ORTHO,
        GYNAC
    }


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


	public Speciality getSpeciality() {
		return speciality;
	}


	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public List<Patient> getPatients() {
		return patients;
	}


	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}


    
}
