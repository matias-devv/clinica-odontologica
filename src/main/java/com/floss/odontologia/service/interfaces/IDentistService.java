package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Patient;

import java.util.List;

public interface IDentistService {

    //create
    public String createDentist(Dentist dentist);
    //save dentist/user? deberia usar herencia?

    //read
    public Dentist getDentistById(Long id);

    public List<Dentist> getAllDentists();

    public List<Appointment> getAppointmentsByDentist(Dentist dentist);

    public List<Patient> getPatientsByDentist(Dentist dentist);

    //edit
    public String editDentist(Dentist dentist);


}
