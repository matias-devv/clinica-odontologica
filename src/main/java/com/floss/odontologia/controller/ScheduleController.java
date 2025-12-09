package com.floss.odontologia.controller;

import com.floss.odontologia.model.Schedule;
import com.floss.odontologia.service.interfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService iScheduleService;

    @RequestMapping("/create")
    public String createSchedule(@RequestBody Schedule schedule) {
        return iScheduleService.createSchedule(schedule);
    }

    @RequestMapping("/find/{id}")
    public Schedule findScheduleById(@PathVariable Long id) {
        return iScheduleService.getScheduleById(id);
    }

    @RequestMapping("/find-all")
    public List<Schedule> findAllSchedules() {
        return iScheduleService.getAllSchedules();
    }

    @RequestMapping("/edit")
    public void  editSchedule(@RequestBody Schedule schedule) {
        iScheduleService.editSchedule(schedule);
    }
}
