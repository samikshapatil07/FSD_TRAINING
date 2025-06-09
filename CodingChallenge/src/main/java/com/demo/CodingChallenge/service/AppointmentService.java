package com.demo.CodingChallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.Doctor;
import com.demo.CodingChallenge.model.Patient;
import com.demo.CodingChallenge.repository.DoctorRepository;
import com.demo.CodingChallenge.repository.PatientRepository;

@Service
public class AppointmentService {

	   @Autowired
	    private PatientRepository patientRepository;

	    @Autowired
	    private DoctorRepository doctorRepository;
	    
	    

	public AppointmentService(PatientRepository patientRepository, DoctorRepository doctorRepository) {
			super();
			this.patientRepository = patientRepository;
			this.doctorRepository = doctorRepository;
		}


//-----------------API 2: book apponitmnet -----------------
	public String appointment(Integer patientId, Integer doctorId) {
		Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
        
        // Add doctor to patient's list if not already added
        List<Doctor> doctorList = patient.getDoctors();
        if (!doctorList.contains(doctor)) {
            doctorList.add(doctor);
            patient.setDoctors(doctorList);
            patientRepository.save(patient);
        }

        return "Appointment booked successfully between patient " + patient.getName() + " and doctor " + doctor.getName();
    }

}
