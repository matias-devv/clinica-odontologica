package com.floss.odontologia.controller;

import com.floss.odontologia.dto.response.AppointmentDTO;
import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.service.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private IAppointmentService iAppointmentService;

    @PostMapping("/create")
    public String createAppointment(@RequestBody Appointment appointment){
        return iAppointmentService.createAppo(appointment);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id){

        AppointmentDTO dto = iAppointmentService.getAppointmentById(id);
        if(dto != null){
            return ResponseEntity.status(200).body(dto);
        }
        return ResponseEntity.status(404).body("Appointment not found");
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> getAllAppointments(){

        List<AppointmentDTO> listDto = iAppointmentService.getAllAppointments();
        if( listDto != null){
            return ResponseEntity.status(200).body(listDto);
        }
        return ResponseEntity.status(404).body("The list of appointments is empty");
    }

    @GetMapping("/appointments-today")
    public int getAppointmentNumberToday(@RequestBody Dentist dentist){
        return iAppointmentService.getAppointmentNumberToday(dentist);
    }

    @GetMapping("/hours/{date}/{selectedDay}")
    //posiblemente deba mejorarlo con Dtos.
    public List<LocalTime> getHoursOfDentist(
                                             @PathVariable LocalDate date,
                                             @RequestBody Dentist dentist,
                                             @PathVariable String selectedDay){
        return iAppointmentService.getHoursOfDentist(date, dentist, selectedDay);
    }

    @PutMapping("/edit")
    public String editAppointment(@RequestBody Appointment appointment){
        return iAppointmentService.editAppo(appointment);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable Long id){
        return iAppointmentService.deleteAppo(id);
    }
}
