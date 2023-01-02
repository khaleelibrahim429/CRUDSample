package com.sos.crud.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "patient")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Patient {

    @Id
    private int patientId;
    private String patientName;
    private Date dob;
    private String address;
    private String contactNo;
    private String disease;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "doctor_doctor_id", referencedColumnName = "doctorId")
    private Doctor doctor;


}
