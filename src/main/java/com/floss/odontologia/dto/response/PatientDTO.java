package com.floss.odontologia.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter @Setter
public class PatientDTO {

    private Long id_patient;
    private String name;
    private String surname;
    private String dni;
    private String age;
    private LocalTime date_of_birth;
    private Boolean insurance;
    private String patient_condition; // condition -> reserved word for sql
    private Boolean routine;
    private String treatment_type;
}
