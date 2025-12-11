package com.floss.odontologia.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class AppointmentDTO {

    private Long id_appointment;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private Long id_dentist;
    private String name_dentist;
    private String surname_dentist;
    private Long id_patient;
    private String name_patient;
    private String surname_patient;
}
