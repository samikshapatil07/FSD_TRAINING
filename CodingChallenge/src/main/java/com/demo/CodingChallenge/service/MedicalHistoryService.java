package com.demo.CodingChallenge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.dto.MedicalHistoryDto;
import com.demo.CodingChallenge.model.MedicalHistory;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.repository.MedicalHistoryRepository;

@Service
public class MedicalHistoryService {

    private final PatientService patientService;
    private final MedicalHistoryRepository medicalHistoryRepository;

    public MedicalHistoryService(PatientService patientService, MedicalHistoryRepository medicalHistoryRepository) {
        this.patientService = patientService;
        this.medicalHistoryRepository = medicalHistoryRepository;
    }

    /*
    I have written a separate logic in patient service for adding patient so if patient exists already then:
    1. Fetch Id from username
    2. Set id to patient
    3. Set patient to medical history
    If not:
    1. Add patient using service
    2. Set that patient to medicalHistory
     */

    public MedicalHistory addPatientWithMedicalRecords(MedicalHistory medicalHistory) {
        Patient patient = medicalHistory.getPatient();
        Patient patientToCheck = patientService.getByUsername(patient.getUser().getUsername());
        if(patientToCheck!=null){
            patient.setId(patientToCheck.getId());
            medicalHistory.setPatient(patient);
        }
        else{
            patient = patientService.addPatient(patient);
            medicalHistory.setPatient(patient);
        }
        return medicalHistoryRepository.save(medicalHistory);
    }

    /*
    As one patient can have many medical histories when called using patientId it will return list of medicalHistories of patients.
     */

    public List<MedicalHistoryDto> getMedicalHistorysOfPatient(int patientId) {
        List<MedicalHistory> medicalHistories =  medicalHistoryRepository.getByPatientId(patientId);
        List<MedicalHistoryDto> medicalHistoryDtos = new ArrayList<>();
        for(MedicalHistory medicalHistory: medicalHistories){
            MedicalHistoryDto medicalHistoryDto = new MedicalHistoryDto();
            medicalHistoryDto.setIllness(medicalHistory.getIllness());
            medicalHistoryDto.setCurrentMedication(medicalHistory.getCurrentMedication());
            medicalHistoryDto.setNoOfYears(medicalHistory.getNumOfYears());
            medicalHistoryDto.setPatientName(medicalHistory.getPatient().getName());
            medicalHistoryDtos.add(medicalHistoryDto);
        }
        return medicalHistoryDtos;
    }
}
