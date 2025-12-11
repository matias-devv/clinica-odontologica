package com.floss.odontologia.service.impl;

import com.floss.odontologia.dto.response.ScheduleDTO;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Schedule;
import com.floss.odontologia.repository.IScheduleRepository;
import com.floss.odontologia.service.interfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    @Autowired
    private IScheduleRepository iScheduleRepository;

    @Override
    public String createSchedule(Schedule schedule) {
        iScheduleRepository.save(schedule);
        return "The schedule was saved successfully";
    }

    @Override
    public Schedule getScheduleById(Long id) {
        return iScheduleRepository.findById(id).orElse(null);
    }

    @Override
    public List<ScheduleDTO> getAllDentistSchedules(Long id) {
        List<Schedule> list = iScheduleRepository.findAll();
        List<ScheduleDTO> scheduleDTOs = new ArrayList<>();

        for (Schedule schedule : list) {
            if (schedule.getDentist().getId_dentist() == id) {
                //for each entity -> dto
                ScheduleDTO scheduleDTO = this.setAttributesDto(schedule);
                //dto -> list
                scheduleDTOs.add(scheduleDTO);
            }
        }
        return scheduleDTOs;
    }

    @Override
    public void editSchedule(Schedule schedule) {
        iScheduleRepository.save(schedule);
    }

    @Override
    public ScheduleDTO setAttributesDto(Schedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setDate_from(schedule.getDate_from());
        dto.setDate_to(schedule.getDate_to());
        dto.setStart_time(schedule.getStartTime());
        dto.setEnd_time(schedule.getEndTime());
        dto.setId_dentist(schedule.getDentist().getId_dentist());
        dto.setName_dentist(schedule.getDentist().getName());
        dto.setActive(schedule.isActive());
        return dto;
    }
}
