package com.sos.crud.service;

import com.sos.crud.bean.Patient;

import java.util.List;

public interface PatientService {


    List<Patient> getAllPatientDetails();

    Patient getPatientById(int patientId);

    Patient addPatient(Patient patient);

    Patient updatePatient(int patientId, Patient patient);

    void deletePatientById(int patientId);

    Patient allocateDoctor(int patientId, int doctorId);
}
