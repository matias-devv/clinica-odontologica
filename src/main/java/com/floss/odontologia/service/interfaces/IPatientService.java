package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.model.Patient;

import java.util.List;

public interface IPatientService {

    //create
    public String createPatient(Patient patient);

    //read
    public Patient getPatient(String dni);

    public List<Patient> getPatients();

    public int getTotalOfPatients();

    public List<Patient> getPatientsWithInsurance();

    public List<Patient> getPatientsWithoutInsurance();

    //update
    public String editPatient(Patient patient);

    //delete
    public String deletePatient(Long id);


}
