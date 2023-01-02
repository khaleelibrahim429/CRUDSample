package com.sos.crud.repository;

import com.sos.crud.bean.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
}
