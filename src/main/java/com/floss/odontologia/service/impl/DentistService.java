package com.floss.odontologia.service.impl;

import com.floss.odontologia.dto.response.AppointmentDTO;
import com.floss.odontologia.dto.response.DentistDTO;
import com.floss.odontologia.dto.response.PatientDTO;
import com.floss.odontologia.dto.response.ScheduleDTO;
import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Schedule;
import com.floss.odontologia.repository.IDentistRepository;
import com.floss.odontologia.service.interfaces.IAppointmentService;
import com.floss.odontologia.service.interfaces.IDentistService;
import com.floss.odontologia.service.interfaces.IPatientService;
import com.floss.odontologia.service.interfaces.IScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DentistService implements IDentistService {

    @Autowired
    private IDentistRepository iDentistRepository;

    @Autowired
    private IAppointmentService iAppointmentService;

    @Autowired
    private IPatientService iPatientService;

    @Autowired
    private IScheduleService iScheduleService;

    @Override
    public void createDentist(Dentist dentist) {
        iDentistRepository.save(dentist);
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
    public List<AppointmentDTO> getAppointmentsByDentist(Long id) {

        List <AppointmentDTO> AppoListDtos = iAppointmentService.getAllAppointments();
        List <AppointmentDTO> cleanList = new ArrayList<>();

        for ( AppointmentDTO appointmentDTO : AppoListDtos){

            //If the dentist are the same as the one asigned to the appointment -> add it to the clean list
            if ( appointmentDTO.getId_dentist() == id){
                cleanList.add(appointmentDTO);
            }
        }
        return cleanList;
    }

    @Override
    public List<PatientDTO> getPatientsByDentist(Long id) {

        List <PatientDTO> patientsListDtos = iPatientService.getPatients();
        List <AppointmentDTO> appoListDtos = iAppointmentService.getAllAppointments();

        List <PatientDTO> cleanList = new ArrayList<>();

        for (PatientDTO patientDto : patientsListDtos){
            for (AppointmentDTO appoDto : appoListDtos){
                //If the dentist asigned to the appointment is the same as the one that I received && and the patient is the same
                // -> add it the patient to the cleanList
                if( appoDto.getId_dentist() == id && appoDto.getId_patient() == patientDto.getId_patient()){

                    cleanList.add(patientDto);
                }
            }
        }
        cleanList = this.avoidDuplicates(cleanList);

        return cleanList;
    }

    private List<PatientDTO> avoidDuplicates(List<PatientDTO> cleanList) {

        //I go through the entire list
        for ( int i = 0; i < cleanList.size() - 1 ; i++){

           //If the next patient it's not null
            if(cleanList.get(i + 1) != null) {
                //If the current patient and the next has the same id
                if ( cleanList.get(i).getId_patient() == cleanList.get(i + 1).getId_patient() ) {
                    //remove the current patient
                    cleanList.remove(i);
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

        //clean list of schedules for the dto
        dto = this.setAttributesOfSchedulesDtos(dentist.getSchedulesList(), dto);
        dto = this.setAttributesOfAppointmentsDtos(dentist.getAppointmentList(), dto);
        return dto;
    }

    private DentistDTO setAttributesOfSchedulesDtos(List<Schedule> schedulesList, DentistDTO dto) {

        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        if(schedulesList != null){
            for (Schedule schedule : schedulesList) {
                 //for each schedule -> dto
                 ScheduleDTO scheduleDto = iScheduleService.setAttributesDto(schedule);
                 //dto -> list dtos
                 scheduleDTOS.add(scheduleDto);
            }
        }
        //I set the list of the schedules to the dentistDTO
        dto.setSchedules(scheduleDTOS);
        return dto;
    }

    private DentistDTO setAttributesOfAppointmentsDtos(List<Appointment> appointmentList, DentistDTO dto) {

        List<AppointmentDTO> appointmentDTOS = new ArrayList<>();
        if( appointmentList != null){
            for (Appointment appointment : appointmentList) {
                //for each appointment -> dto
                AppointmentDTO appoDto = iAppointmentService.setAttributesDto(appointment);
                //dto -> list dtos
                appointmentDTOS.add(appoDto);
            }
        }
        //I set the list of the schedules to the dentistDTO
        dto.setAppointments(appointmentDTOS);
        return dto;
    }
}
