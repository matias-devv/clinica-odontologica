package com.floss.odontologia.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity @Getter @Setter
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String dayWeek;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalDate date_from;
    private LocalDate date_to;
    private boolean active;

    @ManyToOne
    @JoinColumn(name="id_dentist")
    private Dentist dentist;

}
