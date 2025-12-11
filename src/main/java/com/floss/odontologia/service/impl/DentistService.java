package com.floss.odontologia.service.impl;

import com.floss.odontologia.dto.response.AppointmentDTO;
import com.floss.odontologia.dto.response.DentistDTO;
import com.floss.odontologia.dto.response.PatientDTO;
import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Patient;
import com.floss.odontologia.repository.IAppointmentRepository;
import com.floss.odontologia.repository.IDentistRepository;
import com.floss.odontologia.repository.IPatientRepository;
import com.floss.odontologia.service.interfaces.IAppointmentService;
import com.floss.odontologia.service.interfaces.IDentistService;
import com.floss.odontologia.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DentistService implements IDentistService {

    @Autowired
    private IDentistRepository iDentistRepository;

    @Autowired
    private IAppointmentService iAppointmentService;

    @Autowired
    private IPatientService iPatientService;

    @Override
    public String createDentist(Dentist dentist) {
        iDentistRepository.save(dentist);
        return "The dentist was created successfully";
    }

    @Override
    public DentistDTO getDentistById(Long id) {
        Dentist dentist = iDentistRepository.findById(id).orElse(null);
        return this.setAttributesDto(dentist);
    }

    @Override
    public List<DentistDTO> getAllDentists() {

        List<Dentist> listDentist =  iDentistRepository.findAll();
        List<DentistDTO> listDentistDTO = new ArrayList<>();

        for (Dentist dentist : listDentist) {
            //for each dentist -> dto
            DentistDTO dentistDto = this.setAttributesDto(dentist);
            //dto -> list dto
            listDentistDTO.add(dentistDto);
        }
        return listDentistDTO;
    }

    @Override
    public List<AppointmentDTO> getAppointmentsByDentist(Dentist dentist) {

        List <AppointmentDTO> AppoListDtos = iAppointmentService.getAllAppointments();
        List <AppointmentDTO> cleanList = new ArrayList<>();

        for ( AppointmentDTO appointmentDTO : AppoListDtos){
            //If the dentist are the same as the one asigned to the appointment -> add it to the clean list
            if ( appointmentDTO.getId_dentist() == dentist.getId_dentist()){
                cleanList.add(appointmentDTO);
            }
        }
        return cleanList;
    }

    @Override
    public List<PatientDTO> getPatientsByDentist(Dentist dentist) {

        List <PatientDTO> patientsListDtos = iPatientService.getPatients();
        List <AppointmentDTO> appoListDtos = iAppointmentService.getAllAppointments();

        List <PatientDTO> cleanList = new ArrayList<>();

        for (PatientDTO patientDto : patientsListDtos){
            for (AppointmentDTO appoDto : appoListDtos){
                //If the dentist asigned to the appointment is the same as the one that I received && and the patient is the same
                // -> add it the patient to the cleanList
                if( appoDto.getId_dentist() == dentist.getId_dentist() && appoDto.getId_patient() == patientDto.getId_patient()){

                    cleanList.add(patientDto);
                }
            }
        }
        return cleanList;
    }

    @Override
    public String editDentist(Dentist dentist) {
        iDentistRepository.save(dentist);
        return "The dentist was edited succesfully";
    }

    @Override
    public DentistDTO setAttributesDto(Dentist dentist){
        DentistDTO dto = new DentistDTO();
        dto.setId_dentist(dentist.getId_dentist());
        dto.setName(dentist.getName());
        dto.setSurname(dentist.getSurname());
        dto.setDni(dentist.getDni());
        dto.setSpecialty(dentist.getSpeciality().getName());
        return dto;
    }
}
