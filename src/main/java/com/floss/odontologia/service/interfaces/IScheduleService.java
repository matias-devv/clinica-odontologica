package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.model.Schedule;

import java.util.List;

public interface IScheduleService {

    //create
    public String createSchedule(Schedule schedule);

    //read
    public Schedule getScheduleById(Long id);

    public List<Schedule> getAllSchedules();

    public void editSchedule(Schedule schedule);
}
