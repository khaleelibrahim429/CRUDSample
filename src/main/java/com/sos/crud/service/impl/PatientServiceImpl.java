package com.sos.crud.service.impl;

import com.sos.crud.bean.Doctor;
import com.sos.crud.bean.Patient;
import com.sos.crud.exception.ResourceNotFoundException;
import com.sos.crud.repository.DoctorRepository;
import com.sos.crud.repository.PatientRepository;
import com.sos.crud.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    public PatientRepository patientRepository;

    @Autowired
    public DoctorRepository doctorRepository;

    Logger log = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Override
    public List<Patient> getAllPatientDetails()
    {
        List<Patient> patients = new ArrayList<>();
        patientRepository.findAll().forEach(patients::add);
        return patients;
    }

    @Override
    public Patient getPatientById(int patientId)
    {
        return patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient","Id",patientId));
    }

    public Patient addPatient(Patient patient)
    {
        if (patient ==null)
        {
            throw new RuntimeException("No such patient");
        }
        else return patientRepository.save(patient);
    }
    @Override
    public Patient updatePatient(int patientId, Patient patient) {
        log.info("Updating patient details with PatientId : {}", patientId);
        Patient currentPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));

        currentPatient.setPatientName(patient.getPatientName());
        currentPatient.setDisease(patient.getDisease());
        currentPatient.setDob(patient.getDob());
        currentPatient.setAddress(patient.getAddress());
        currentPatient.setContactNo(patient.getContactNo());

        return patientRepository.save(patient);
    }

    @Override
    public void deletePatientById(int patientId) {
        log.info("Deleting patient with patient Id " + patientId);
        Patient patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new RuntimeException("No Such patient exists"));
        if(patient.getDoctor() != null) {
            Doctor doctor = patient.getDoctor();
            doctor.deAllocatePatient(patient);
            log.info("Patient {} has been deallocated from Doctor {}",patient.getPatientName(), doctor.getDoctorName());
            doctorRepository.save(doctor);
        }
        patientRepository.deleteById(patientId);
    }

    @Override
    public Patient allocateDoctor(int patientId, int doctorId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("No Such patient exists"));
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("No such Doctor exists"));
        patient.setDoctor(doctor);
        patientRepository.save(patient);
        log.info("Patient " +  patient.getPatientName() + " has been allocated to Doctor " + doctor.getDoctorName() );
        return patient;
    }
}
