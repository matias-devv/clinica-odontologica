package com.floss.odontologia.controller;

import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Patient;
import com.floss.odontologia.service.interfaces.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private IDentistService iDentistService;

    @RequestMapping("/create")
    public String createDentist(@RequestBody Dentist dentist){
        return iDentistService.createDentist(dentist);
    }

    @RequestMapping("/find/{id}")
    public Dentist findDentistById(@PathVariable Long id){
        return iDentistService.getDentistById(id);
    }

    @RequestMapping("/find-all")
    public List<Dentist> findAllDentist(){
        return iDentistService.getAllDentists();
    }

    @RequestMapping("/appointments-dentist")
    public List<Appointment> getAppointmentsByDentist(@RequestBody Dentist dentist){
        return iDentistService.getAppointmentsByDentist(dentist);
    }

    @RequestMapping("/patients-dentist")
    public List<Patient> getPatientsByDentist(@RequestBody Dentist dentist){
        return iDentistService.getPatientsByDentist(dentist);
    }

    @RequestMapping("/edit")
    public String editDentist(@RequestBody Dentist dentist){
        return iDentistService.editDentist(dentist);
    }
}
