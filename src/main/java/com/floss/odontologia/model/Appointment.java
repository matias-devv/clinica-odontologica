package com.floss.odontologia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity @Getter @Setter
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_appointment;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name="id_dentist")
    private Dentist dentist;

    @ManyToOne
    @JoinColumn(name="id_patient")
    private Patient patient;

}
