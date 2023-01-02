package com.sos.crud.service;

import com.sos.crud.bean.Doctor;
import com.sos.crud.bean.Patient;

import java.util.List;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);

    List<Doctor> getAllDoctorDetails();

    Doctor getDoctorById(Integer doctorId);

    Doctor updateDoctor(int doctorId, Doctor doctor);

    void deleteDoctorById(Integer doctorId);

    void deAllocatePatient(int doctorId, int patientId);
}
