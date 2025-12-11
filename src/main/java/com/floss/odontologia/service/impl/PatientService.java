package com.floss.odontologia.service.impl;

import com.floss.odontologia.dto.response.PatientDTO;
import com.floss.odontologia.model.Patient;
import com.floss.odontologia.repository.IPatientRepository;
import com.floss.odontologia.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository iPatientRepository;

    @Override
    public String createPatient(Patient patient) {
        iPatientRepository.save(patient);
        return "The patient was saved successfully";
    }

    @Override
    public PatientDTO getPatient(String dni) {

        List<Patient> listPatients = iPatientRepository.findAll();

        for (Patient pa : listPatients) {
            //If the dni is the same -> return patient
            if ( pa.getDni().equals(dni)) {
                Patient patient = iPatientRepository.findById(pa.getId_patient()).orElse(null);
                return this.setAttributesDto(patient);
            }
        }
        return null;
    }

    @Override
    public List<PatientDTO> getPatients() {
        List<Patient> list = iPatientRepository.findAll();
        List<PatientDTO> listPatientsDto = new ArrayList<>();

        for( Patient pa : list){
            //for each patient -> dto
            PatientDTO patientDto = this.setAttributesDto(pa);
            //dto -> list dto
            listPatientsDto.add(patientDto);
        }
        return listPatientsDto;
    }

    @Override
    public int getTotalOfPatients() {
        return iPatientRepository.findAll().size();
    }

    @Override
    public List<PatientDTO> getPatientsWithInsurance() {
        List<PatientDTO> allPatients = this.getPatients();
        List<PatientDTO> listWithInsurance = new ArrayList<>();

        for (PatientDTO patientDto : allPatients) {
            if( patientDto.getInsurance() == true){
                listWithInsurance.add(patientDto);
            }
        }
        return listWithInsurance;
    }

    @Override
    public List<PatientDTO> getPatientsWithoutInsurance() {
        List<PatientDTO> allPatients = this.getPatients();
        List<PatientDTO> listWithoutInsurance = new ArrayList<>();

        for (PatientDTO patientDto : allPatients) {
            if( patientDto.getInsurance() == false){
                listWithoutInsurance.add(patientDto);
            }
        }
        return listWithoutInsurance;
    }

    @Override
    public String editPatient(Patient patient) {
        iPatientRepository.save(patient);
        return "The patient was edited successfully";
    }

    @Override
    public String deletePatient(Long id) {
        try{
            iPatientRepository.deleteById(id);
            return "The patient was deleted successfully";
        }catch (Exception e) {
            return "The patient does not exist in the database";
        }
    }


    @Override
    public PatientDTO setAttributesDto(Patient patient) {
        PatientDTO dto = new PatientDTO();
        dto.setId_patient(patient.getId_patient());
        dto.setName(patient.getName());
        dto.setSurname(patient.getSurname());
        dto.setDni(patient.getDni());
        dto.setAge(patient.getAge());
        dto.setDate_of_birth(patient.getDate_of_birth());
        dto.setInsurance(patient.getInsurance());
        dto.setPatient_condition(patient.getPatient_condition());
        dto.setRoutine(patient.getRoutine());
        dto.setTreatment_type(patient.getTreatment_type());
        return dto;
    }
}
