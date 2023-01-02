package com.sos.crud.controller;

import com.sos.crud.bean.Patient;
import com.sos.crud.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    private String getStatus()
    {
        return "Application is up";
    }

    @GetMapping("/getAllPatients")
    public List<Patient> getAllPatientDetails()
    {
        return patientService.getAllPatientDetails();
    }

    @GetMapping("/getPatient/{patientId}")
    public Patient getPatientById(@PathVariable int patientId )
    {
        return patientService.getPatientById(patientId);
    }

    @PostMapping(value = "/addPatient")
    public Patient addPatient(@RequestBody Patient patient)
    {

        return patientService.addPatient(patient);
    }

    @PutMapping(value = "/updatePatient/{patientId}")
    public Patient updatePatient(@PathVariable("patientId") int patientId, @RequestBody Patient patient)
    {
        log.info("Updating patient details..");
        return patientService.updatePatient(patientId,patient);
    }

    @DeleteMapping(value = "/deletePatient/{patientId}")
    public String deletePatientById(@PathVariable int patientId)
    {
        patientService.deletePatientById(patientId);
        String msg = "Patient with patient Id " + patientId + " has been deleted";
        log.info(msg);
        return msg;
    }

@PutMapping("/{patientId}/allocateDoctor/{doctorId}")
    public Patient allocateDoctor(@PathVariable int patientId, @PathVariable int doctorId)
    {
        return patientService.allocateDoctor(patientId, doctorId);
    }

}
