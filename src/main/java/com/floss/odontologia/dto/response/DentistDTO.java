package com.floss.odontologia.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DentistDTO {
    private Long id_dentist;

    private String name;
    private String surname;
    private String dni;
    private String specialty;

}