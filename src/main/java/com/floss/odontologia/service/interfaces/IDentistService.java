package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.dto.response.AppointmentDTO;
import com.floss.odontologia.dto.response.DentistDTO;
import com.floss.odontologia.dto.response.PatientDTO;
import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Patient;

import java.util.List;

public interface IDentistService {

    //create
    public void createDentist(Dentist dentist);

    //read
    public DentistDTO getDentistById(Long id);

    public List<DentistDTO> getAllDentists();

    public List<AppointmentDTO> getAppointmentsByDentist(Long id);

    public List<PatientDTO> getPatientsByDentist(Long id);

    //edit
    public String editDentist(Dentist dentist);

    public DentistDTO setAttributesDto (Dentist dentist);

}
