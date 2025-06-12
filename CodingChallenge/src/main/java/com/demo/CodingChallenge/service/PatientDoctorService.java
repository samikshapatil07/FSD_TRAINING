package com.demo.CodingChallenge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.dto.PatientDto;
import com.demo.CodingChallenge.exception.InvalidIdException;
import com.demo.CodingChallenge.exception.ResourceNotFoundException;
import com.demo.CodingChallenge.model.Doctor;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.model.PatientDoctor;
import com.demo.CodingChallenge.repository.DoctorRepository;
import com.demo.CodingChallenge.repository.PatientDoctorRepository;
import com.demo.CodingChallenge.repository.PatientRepository;

@Service
public class PatientDoctorService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PatientDoctorRepository patientDoctorRepository;


    public PatientDoctorService(PatientRepository patientRepository, DoctorRepository doctorRepository, PatientDoctorRepository patientDoctorRepository, MedicalHistoryService medicalHistoryService, PatientDto patientDto) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.patientDoctorRepository = patientDoctorRepository;

    }

    /*
    To create appointment we must know following
    1. Whether patient exists using patient Id else throw exception
    2. Whether Doctor exists using doctor id else throw exception
    3. Setting doctor to patientDoctor object
    4. Setting patient to patientDoctor object
    5. Creating appointment and saving in the db
     */

    public PatientDoctor createAppointment(int patientId, int doctorId, PatientDoctor patientDoctor) {
        if(doctorId==0 || doctorId<0){
            throw new InvalidIdException("Invalid Doctor Id");
        }

        if(patientId == 0 || patientId < 0){
            throw new InvalidIdException("Invalid Patient Id");
        }

        Patient patient = patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Patient Not found"));
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor not Found"));
        patientDoctor.setPatient(patient);
        patientDoctor.setDoctor(doctor);
        return patientDoctorRepository.save(patientDoctor);
    }
    /*
    As one doctor can have many patients it will return list of patients
     */
    public List<Patient> getPatientsByDoctor(int doctorId) {

        if(doctorId==0 || doctorId<0){
            throw new InvalidIdException("Invalid Doctor Id");
        }

        List<Patient> patients = patientDoctorRepository.getPatientsByDoctor(doctorId);
        return patients;
    }
}