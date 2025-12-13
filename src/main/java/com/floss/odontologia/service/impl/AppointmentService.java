package com.floss.odontologia.service.impl;

import com.floss.odontologia.dto.response.AppointmentDTO;
import com.floss.odontologia.dto.response.DentistDTO;
import com.floss.odontologia.dto.response.ScheduleDTO;
import com.floss.odontologia.model.Appointment;
import com.floss.odontologia.model.Dentist;
import com.floss.odontologia.model.Schedule;
import com.floss.odontologia.repository.IAppointmentRepository;
import com.floss.odontologia.repository.IDentistRepository;
import com.floss.odontologia.service.interfaces.IAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService implements IAppointmentService {

    @Autowired
    private IAppointmentRepository iAppointmentRepository;

    @Autowired
    private IDentistRepository iDentistRepository;

    @Override
    public String createAppo(Appointment appointment) {
        iAppointmentRepository.save(appointment);
        return "The appointment was saved correctly";
    }

    @Override
    public AppointmentDTO getAppointmentById(Long id) {
        Appointment appo = iAppointmentRepository.findById(id).orElse(null);
        return this.setAttributesDto(appo);
    }

    @Override
    public List<AppointmentDTO> getAllAppointments() {
        List<Appointment> list = iAppointmentRepository.findAll();
        List<AppointmentDTO> listDto = new ArrayList<>();

        for(Appointment appointment : list){
            AppointmentDTO dto = this.setAttributesDto(appointment);
            listDto.add(dto);
        }
        return listDto;
    }

    @Override
    public int getAppointmentNumberToday(Long id) {

        int total = 0;
        LocalDate today = LocalDate.now();
        //I find the dentist
        Dentist dentist = iDentistRepository.findById(id).orElse(null);
        //I'm bringing the dentist appointments
        if( dentist != null ){

            List <Appointment> listAppo = dentist.getAppointmentList();
            for (Appointment appo : listAppo){

                LocalDate dateAppo = appo.getDate();
                if (dateAppo.equals(today)){
                    total++;
                }
            }
        }
        return total;
    }

    @Override
    public List<LocalTime> getHoursOfDentist(LocalDate choosenDate, Long id_dentist, String selectedDay) {
        //Max has that amount to prevent it from returning too many time slots.
        int max = 20;
        int counter = 0;

        //I get the schedules of the dentist
        Dentist dentist = iDentistRepository.findById(id_dentist).orElse(null);
        if ( dentist != null) {

            List<Schedule> schedules = dentist.getSchedulesList();
            List<LocalTime> hours = new ArrayList<>();

            if (schedules != null) {
                for (Schedule schedule : schedules) {

                    boolean result = this.verifySchedule( schedule, choosenDate, selectedDay);

                    //if result is ok
                    if (result) {
                        //this "slot" is a sort of "accumulator"
                        LocalTime slot = schedule.getStartTime();

                        //as long as the "accumulator" is less than the (end time - 30 minutes)
                        while (slot.isBefore(schedule.getEndTime().minusMinutes(30))) {

                            //I add 30 minutes to this initial “slot” and add it to the list of “hours.”
                            slot = slot.plusMinutes(30);
                            hours.add(slot);
                            counter++;

                            //If the slots are over "twenty" I return the list because is too much slots for one work day
                            if (counter >= max) {
                                return hours;
                            }
                        }
                    }
                }
            }
            //This removes the hours dedicated to other appointments -> they are not available for use
            hours = this.checkAppointments(choosenDate, dentist, hours);
            return hours;
        }
        return null;
    }

    private boolean verifySchedule(Schedule schedule, LocalDate choosenDate, String selectedDay) {
        boolean result = true;
        LocalDate today = LocalDate.now();

            //if the schedule is not active -> null
            if ( !schedule.isActive()) {
                return false;
            }
            //if the day selected and the day in the schedule(String) are not equals -> null
            if ( !schedule.getDayWeek().equalsIgnoreCase(selectedDay)) {
                return false;
            }
            //if the startTime < endTime && the endTime > startTime -> null
            if ( !schedule.getStartTime().isBefore(schedule.getEndTime()) && !schedule.getEndTime().isAfter(schedule.getStartTime())) {
                return false;
            }
            //today must be before the end date(schedule).
            if ( today.isAfter( schedule.getDate_to() )){
                return false;
            }
            //the choosen date must be after the start date(schedule)
            if ( !choosenDate.isAfter( schedule.getDate_from() )){
                return false;
            }
            //the choosen date -> dayWeek(String) is not equals comparing with the dayWeek of the schedule(String) ?
            if ( !choosenDate.getDayOfWeek().toString().equalsIgnoreCase( schedule.getDayWeek() ) ) {
                return false;
            }
        return result;
    }

    @Override
    public List<LocalTime> checkAppointments(LocalDate choosenDate, Dentist dentist, List<LocalTime> hours) {

        List <Appointment> listAppo = dentist.getAppointmentList();
        List <LocalTime> duplicateAppointments = new ArrayList<>();

        //if the appointments are null -> the patient can use the full range of hours in the schedule
        if( listAppo == null){
            return hours;
        }
        for (Appointment appo : listAppo) {

            //I catch the date of the appointment
            LocalDate date = appo.getDate();

            if( date.equals(choosenDate)){
                //I go through the received hours of the schedule
                for (LocalTime slot : hours){

                    // If the start time of the current appointment equals the current slot ->
                    // remove this slot of the list of hours
                    if (appo.getStartTime().equals(slot) ){
                        duplicateAppointments.add(slot);
                    }
                }
            }
        }
        hours.removeAll(duplicateAppointments);
        return hours;
    }

    @Override
    public String editAppo(Appointment appointment) {
        iAppointmentRepository.save(appointment);
        return "The appointment was edited correctly";
    }

    @Override
    public String deleteAppo(Long id) {
        try {
            iAppointmentRepository.deleteById(id);
            return "The appointment was deleted correctly";
        }catch(Exception e) {
            return "The appointment does not exist in the database";
        }
    }

    @Override
    public AppointmentDTO setAttributesDto(Appointment appo) {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setId_appointment(appo.getId_appointment());
        dto.setDate(appo.getDate());
        dto.setStartTime(appo.getStartTime());
        dto.setId_dentist(appo.getDentist().getId_dentist());
        dto.setName_dentist(appo.getDentist().getName());
        dto.setSurname_dentist(appo.getDentist().getSurname());
        dto.setId_patient(appo.getPatient().getId_patient());
        dto.setName_patient(appo.getPatient().getName());
        dto.setSurname_patient(appo.getPatient().getSurname());
        return dto;
    }
}
