package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.dto.response.PatientDTO;
import com.floss.odontologia.model.Patient;

import java.util.List;

public interface IPatientService {

    //create
    public String createPatient(Patient patient);

    //read
    public PatientDTO getPatient(String dni);

    public List<PatientDTO> getPatients();

    public int getTotalOfPatients();

    public List<PatientDTO> getPatientsWithInsurance();

    public List<PatientDTO> getPatientsWithoutInsurance();

    //update
    public String editPatient(Patient patient);

    //delete
    public String deletePatient(Long id);

    public PatientDTO setAttributesDto(Patient patient);

}
