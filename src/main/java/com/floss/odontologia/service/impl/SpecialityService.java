package com.floss.odontologia.service.impl;

import com.floss.odontologia.model.Speciality;
import com.floss.odontologia.repository.ISpecialityRepository;
import com.floss.odontologia.service.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialityService implements ISpecialityService {

    @Autowired
    private ISpecialityRepository iSpecialityRepository;


    @Override
    public Speciality getSpecialityByName(String name) {

        List<Speciality> listSpe = this.getAllSpecialities();
        Speciality speFound = new Speciality();

        for (Speciality spe : listSpe) {

            if ( spe.getName().equals(name) ) {
                speFound = iSpecialityRepository.findById( spe.getId_speciality() ).orElse(null);
                return speFound;
            }
        }
        return speFound;
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return iSpecialityRepository.findAll();
    }

    @Override
    public void editSpeciality(Speciality speciality) {
        iSpecialityRepository.save(speciality);
    }
}
