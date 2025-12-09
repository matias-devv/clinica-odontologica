package com.floss.odontologia.controller;

import com.floss.odontologia.model.Patient;
import com.floss.odontologia.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService iPatientService;

    @RequestMapping("/create")
    public String createPatient(@RequestBody Patient patient){
        return iPatientService.createPatient(patient);
    }

    @RequestMapping("/find/{id}")
    public Patient findPatient(@PathVariable String dni){
        return iPatientService.getPatient(dni);
    }

    @RequestMapping("/find-all")
    public List<Patient> findAllPatients(){
        return iPatientService.getPatients();
    }

    @RequestMapping("/total-patients")
    public int totalPatients(){
        return iPatientService.getTotalOfPatients();
    }

    @RequestMapping("/with-insurance")
    public List<Patient> withInsurance(){
        return iPatientService.getPatientsWithInsurance();
    }

    @RequestMapping("/without-insurance")
    public List<Patient> withoutInsurance(){
        return iPatientService.getPatientsWithoutInsurance();
    }

    @RequestMapping("/edit")
    public String editPatient(@RequestBody Patient patient){
        return iPatientService.editPatient(patient);
    }

    @RequestMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id){
        return iPatientService.deletePatient(id);
    }
}
