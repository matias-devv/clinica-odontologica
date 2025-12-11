package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.dto.response.ScheduleDTO;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Schedule;

import java.util.List;

public interface IScheduleService {

    //create
    public String createSchedule(Schedule schedule);

    //read
    public Schedule getScheduleById(Long id);

    public List<ScheduleDTO> getAllDentistSchedules(Long id);

    public void editSchedule(Schedule schedule);

    public ScheduleDTO setAttributesDto(Schedule schedule);
}
