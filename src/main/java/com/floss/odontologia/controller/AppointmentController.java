package com.floss.odontologia.controller;

import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.service.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @RequestMapping("/create")
    public String createAppointment(@RequestBody Appointment appointment){
        return iAppointmentService.createAppo(appointment);
    }

    @RequestMapping("/find/{id}")
    public Appointment getAppointmentById(@PathVariable Long id){
        return iAppointmentService.getAppointmentById(id);
    }

    @RequestMapping("/find-all")
    public List<Appointment> getAllAppointments(){
        return iAppointmentService.getAllAppointments();
    }

    @RequestMapping("/appointments-today")
    public int getAppointmentNumberToday(@RequestBody Dentist dentist){
        return iAppointmentService.getAppointmentNumberToday(dentist);
    }

    @RequestMapping("/edit")
    public String editAppointment(@RequestBody Appointment appointment){
        return iAppointmentService.editAppo(appointment);
    }

    @RequestMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id){
        return iAppointmentService.deleteAppo(id);
    }
}
