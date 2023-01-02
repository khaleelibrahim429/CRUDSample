package com.sos.crud.controller;

import com.sos.crud.bean.Doctor;
import com.sos.crud.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DoctorController {

    @Autowired
private DoctorService doctorService;

    @GetMapping(value = "/getAllDoctors")
    public List<Doctor> getAllDoctorDetails()
    {
        return doctorService.getAllDoctorDetails();
    }

    @GetMapping(value = "/getDoctor/{doctorId}")
    public Doctor getDoctorById(@PathVariable int doctorId)
    {
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping(value = "/addDoctor")
    public Doctor addDoctor(@RequestBody Doctor doctor)
    {
        return doctorService.addDoctor(doctor);

    }
    @PutMapping(value = "/updateDoctor/{doctorId}")
    public Doctor updateDoctor(@PathVariable("doctorId") int doctorId, @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(doctorId, doctor);

    }
    @DeleteMapping(value = "/deleteDoctor/{doctorId}")
    public String deleteDoctorById(@PathVariable Integer doctorId)
    {
        doctorService.deleteDoctorById(doctorId);
            return "Doctor with id " +doctorId + " has been deleted..!!";
    }

    @PutMapping("/{doctorId}/deAllocate/{patientId}")
    public ResponseEntity<String> deAllocatePatient(@PathVariable("doctorId") int doctorId, @PathVariable("patientId") int patientId) {
        doctorService.deAllocatePatient(doctorId, patientId);
        return new ResponseEntity<String>("Patient with id " + patientId + " has been de allocated from Doctor", HttpStatus.OK);
    }

    }

