package com.sos.crud.service.impl;

import com.sos.crud.bean.Doctor;
import com.sos.crud.bean.Patient;
import com.sos.crud.exception.ResourceNotFoundException;
import com.sos.crud.repository.DoctorRepository;
import com.sos.crud.repository.PatientRepository;
import com.sos.crud.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);


    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Doctor> getAllDoctorDetails()
    {
        List<Doctor> doctors = new ArrayList<>();
        doctorRepository.findAll().forEach(doctors::add);
        log.info("List of doctors...!!" );
        doctors.forEach(doctor -> log.info(String.valueOf(doctor.getDoctorName())));
        return doctors;
    }

    @Override
    public Doctor getDoctorById(Integer doctorId)
    {
         Doctor doctor = doctorRepository.findById(doctorId)
                 .orElseThrow(() -> new ResourceNotFoundException("Doctor","doctorId",doctorId));
         return doctor;
    }

    @Override
    public Doctor addDoctor(Doctor doctor)
    {
        return doctorRepository.save(doctor);
    }

    @Override
    public Doctor updateDoctor(int doctorId, Doctor doctor)
    {
        log.info("Updating doctor details for doctor Id ; {}", doctorId);
        Doctor currentDoctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));
        currentDoctor.setDoctorName(doctor.getDoctorName());
        return doctorRepository.save(doctor);
    }

    @Override
    public void deleteDoctorById(Integer doctorId)
    {
        Doctor doctor = doctorRepository.findById(doctorId)
        .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));
        if(doctor.getPatients().size() > 0) {
            log.error("Cannot delete Doctor. Doctor with id {} is associated with patient(s)." +
                    "Please deallocate the patients..!!", doctorId);
            throw new RuntimeException();
        }
        else
        {
                doctorRepository.deleteById(doctorId);
            log.info("Doctor with Id : {} is deleted successfully", doctorId);

        }
    }

    @Override
    public void deAllocatePatient(int doctorId, int patientId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", "Id", doctorId));
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", patientId));
        if (patient.getDoctor()==null)
        {
            log.error("Patient is not associated with Doctor. Please check again..!!");
            throw new RuntimeException();
        }
        else
        {
            doctor.deAllocatePatient(patient);
            doctorRepository.save(doctor);
            patientRepository.save(patient);
        }

    }

}
