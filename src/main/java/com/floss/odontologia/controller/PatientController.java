package com.floss.odontologia.controller;

import com.floss.odontologia.dto.response.PatientDTO;
import com.floss.odontologia.model.Patient;
import com.floss.odontologia.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private IPatientService iPatientService;

    @PostMapping("/create")
    public String createPatient(@RequestBody Patient patient){
        return iPatientService.createPatient(patient);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findPatient(@PathVariable String dni){

        PatientDTO dto = iPatientService.getPatient(dni);
        if(dto != null){
            return ResponseEntity.status(200).body(dto);
        }
        return ResponseEntity.status(400).body("The patient was not found");
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllPatients(){

        List<PatientDTO> listDto = iPatientService.getPatients();
        if( listDto != null){
            return ResponseEntity.status(200).body(listDto);
        }
        return ResponseEntity.status(404).body("The list of patients is empty");
    }

    @GetMapping("/total-patients")
    public int totalPatients(){
        return iPatientService.getTotalOfPatients();
    }

    @GetMapping("/with-insurance")
    public ResponseEntity<?> withInsurance(){

        List<PatientDTO> listDto = iPatientService.getPatientsWithInsurance();
        if( listDto != null){
            return ResponseEntity.status(200).body(listDto);
        }
        return ResponseEntity.status(404).body("The list of patients with insurance is empty");
    }

    @GetMapping("/without-insurance")
    public ResponseEntity<?> withoutInsurance(){

        List<PatientDTO> listDto = iPatientService.getPatientsWithoutInsurance();
        if( listDto != null){
            return ResponseEntity.status(200).body(listDto);
        }
        return ResponseEntity.status(404).body("The list of patients without insurance is empty");
    }

    @PutMapping("/edit")
    public String editPatient(@RequestBody Patient patient){
        return iPatientService.editPatient(patient);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id){
        return iPatientService.deletePatient(id);
    }
}
