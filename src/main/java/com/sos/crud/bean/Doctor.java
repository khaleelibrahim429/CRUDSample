package com.sos.crud.bean;

import com.sos.crud.service.impl.DoctorServiceImpl;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Doctor {

    @Id
    private int doctorId;
    private String doctorName;

   // Logger log = LoggerFactory.getLogger(Doctor.class);


    @OneToMany(mappedBy = "doctor")
    private List<Patient> patients;

    public void allocatePatient(Patient patient) {
        if (patients == null) {
            patients = new ArrayList<>();
        }
        patients.add(patient);
        patient.setDoctor(this);
    }

    public Doctor deAllocatePatient(Patient patient)
    {
            patients.remove(patient);
            patient.setDoctor(null);
            return this;

    }
}
