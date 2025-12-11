package com.floss.odontologia.controller;

import com.floss.odontologia.dto.response.ScheduleDTO;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Schedule;
import com.floss.odontologia.service.interfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private IScheduleService iScheduleService;

    @PostMapping("/create")
    public String createSchedule(@RequestBody Schedule schedule) {
        return iScheduleService.createSchedule(schedule);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getScheduleById(@PathVariable Long id){

        Schedule schedule = iScheduleService.getScheduleById(id);
        if (schedule != null){
             ScheduleDTO dto = iScheduleService.setAttributesDto(schedule);
             return ResponseEntity.status(200).body(dto);
        }
        return ResponseEntity.status(404).body("Schedule not found");
    }


    @GetMapping("/find-all/{id}")
    public ResponseEntity<?> getAllDentistSchedules(@PathVariable Long id){

        List<ScheduleDTO> schedules = iScheduleService.getAllDentistSchedules(id);
        if (schedules != null){
            return ResponseEntity.status(200).body(schedules);
        }
        return ResponseEntity.status(404).body("The dentist has no schedules yet");
    }

    @PutMapping("/edit")
    public void  editSchedule(@RequestBody Schedule schedule) {
        iScheduleService.editSchedule(schedule);
    }
}
