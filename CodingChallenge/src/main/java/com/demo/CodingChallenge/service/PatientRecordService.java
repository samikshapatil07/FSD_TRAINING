package com.demo.CodingChallenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.dto.MedicalHistoryDto;
import com.demo.CodingChallenge.dto.PatientDto;
import com.demo.CodingChallenge.model.MedicalHistory;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.repository.PatientRepository;

@Service
public class PatientRecordService {
    
	@Autowired
    private  PatientRepository patientRepository;

	public PatientRecordService(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	//-------------API 4: get patient record--------------
	 public PatientDto getPatientWithHistory(int id) {
	        Patient patient = patientRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Patient not found"));

	        PatientDto dto = new PatientDto();
	        dto.setId(patient.getId());
	        dto.setName(patient.getName());
	        dto.setAge(patient.getAge());

	        List<MedicalHistoryDto> historyDtos = new ArrayList<>();
	        for (MedicalHistory history : patient.getMedicalHistories()) {
	            MedicalHistoryDto mhDto = new MedicalHistoryDto();
	            mhDto.setIllness(history.getIllness());
	            mhDto.setNumOfYears(history.getNum_of_years());
	            mhDto.setCurrent_medication(history.getCurrent_medication());
	            historyDtos.add(mhDto);
	        }

	        dto.setMedicalHistories(historyDtos);

	        return dto;
}
}