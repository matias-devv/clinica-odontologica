package com.floss.odontologia.controller;

import com.floss.odontologia.dto.response.AppointmentDTO;
import com.floss.odontologia.dto.response.DentistDTO;
import com.floss.odontologia.dto.response.PatientDTO;
import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Patient;
import com.floss.odontologia.service.interfaces.IDentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    @Autowired
    private IDentistService iDentistService;

    @PostMapping("/create")
    public String createDentist(@RequestBody Dentist dentist){
        return iDentistService.createDentist(dentist);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findDentistById(@PathVariable Long id){

        DentistDTO dto = iDentistService.getDentistById(id);
        if(dto != null){
            return ResponseEntity.status(200).body(dto);
        }
        return ResponseEntity.status(404).body("The dentist with this id is not found");
    }

    @GetMapping("/find-all")
    public ResponseEntity<?> findAllDentist(){

        List<DentistDTO> listDto = iDentistService.getAllDentists();
        if(listDto != null){
            return ResponseEntity.status(200).body(listDto);
        }
        return ResponseEntity.status(404).body("The list of dentist is empty");
    }

    @GetMapping("/appointments-dentist")
    public ResponseEntity<?> getAppointmentsByDentist(@RequestBody Dentist dentist){
        List<AppointmentDTO> listAppoDto = iDentistService.getAppointmentsByDentist(dentist);
        if(listAppoDto != null){
            return ResponseEntity.status(200).body(listAppoDto);
        }
        return ResponseEntity.status(404).body("There is no appointment with the given dentist");
    }

    @GetMapping("/patients-dentist")
    public ResponseEntity<?> getPatientsByDentist(@RequestBody Dentist dentist){
        List<PatientDTO> listPatientDto = iDentistService.getPatientsByDentist(dentist);
        if(listPatientDto != null){
            return ResponseEntity.status(200).body(listPatientDto);
        }
        return ResponseEntity.status(404).body("There is no patient with the given dentist");
    }

    @PutMapping("/edit")
    public String editDentist(@RequestBody Dentist dentist){
        return iDentistService.editDentist(dentist);
    }
}
