package com.floss.odontologia.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter @Setter
public class ScheduleDTO {

    private Long id;
    private Long id_dentist;
    private String name_dentist;
    private LocalTime start_time;
    private LocalTime end_time;
    private LocalDate date_from;
    private LocalDate date_to;
    private boolean active;


}
