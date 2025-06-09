package com.codingChallenge.project.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "patient_doctor")
public class PatientDoctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    @ManyToOne
    private Patient patient;
    @ManyToOne
    private Doctor doctor;
    @Column(nullable = false, name = "date_of_appointment")
    private LocalDate dateOfAppointment;
    private LocalDate lastDateOfVisit;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDate getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(LocalDate dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public LocalDate getLastDateOfVisit() {
        return lastDateOfVisit;
    }

    public void setLastDateOfVisit(LocalDate lastDateOfVisit) {
        this.lastDateOfVisit = lastDateOfVisit;
    }

}
