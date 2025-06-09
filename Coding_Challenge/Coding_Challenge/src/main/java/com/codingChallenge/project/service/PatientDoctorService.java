package com.codingChallenge.project.service;

import com.codingChallenge.project.dto.MedicalHistoryDto;
import com.codingChallenge.project.dto.PatientDto;
import com.codingChallenge.project.exception.InvalidIdException;
import com.codingChallenge.project.exception.ResourceNotFoundException;
import com.codingChallenge.project.model.Doctor;
import com.codingChallenge.project.model.MedicalHistory;
import com.codingChallenge.project.model.Patient;
import com.codingChallenge.project.model.PatientDoctor;
import com.codingChallenge.project.repository.DoctorRepository;
import com.codingChallenge.project.repository.PatientDoctorRepository;
import com.codingChallenge.project.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientDoctorService {

    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final PatientDoctorRepository patientDoctorRepository;
    private final MedicalHistoryService medicalHistoryService;
    private final PatientDto patientDto;

    public PatientDoctorService(PatientRepository patientRepository, DoctorRepository doctorRepository, PatientDoctorRepository patientDoctorRepository, MedicalHistoryService medicalHistoryService, PatientDto patientDto) {
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.patientDoctorRepository = patientDoctorRepository;
        this.medicalHistoryService = medicalHistoryService;
        this.patientDto = patientDto;
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

        Patient patient = patientRepository.findById(patientId)
        		.orElseThrow(()->new ResourceNotFoundException("Patient Not found"));
        
        Doctor doctor = doctorRepository.findById(doctorId)
        		.orElseThrow(()->new ResourceNotFoundException("Doctor not Found"));
        
        patientDoctor.setPatient(patient);
        patientDoctor.setDoctor(doctor);
        return patientDoctorRepository.save(patientDoctor);
    }
    
    //--------------------------get patient by doctor--------------
    public List<Patient> getPatientsByDoctor(int doctorId) {

        if(doctorId==0 || doctorId<0){
            throw new InvalidIdException("Invalid Doctor Id");
        }

        List<Patient> patients = patientDoctorRepository.getPatientsByDoctor(doctorId);
        return patients;
    }

}
