package com.floss.odontologia.controller;

import com.floss.odontologia.model.Speciality;
import com.floss.odontologia.service.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private ISpecialityService iSpecialityService;

    @RequestMapping("/find/{name}")
    public Speciality findById(@PathVariable String name) {
        return iSpecialityService.getSpecialityByName(name);
    }

    @RequestMapping("/find-all")
    public List<Speciality> findAll() {
        return iSpecialityService.getAllSpecialities();
    }

    @RequestMapping("/edit")
    public void editSpeciality(@RequestBody Speciality speciality) {
        iSpecialityService.editSpeciality(speciality);
    }
}
