package com.floss.odontologia.controller;

import com.floss.odontologia.model.Speciality;
import com.floss.odontologia.service.interfaces.ISpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speciality")
public class SpecialityController {

    @Autowired
    private ISpecialityService iSpecialityService;

    @GetMapping("/find/{name}")
    @ResponseBody
    public Speciality findById(@PathVariable String name) {
        return iSpecialityService.getSpecialityByName(name);
    }

    @GetMapping("/find-all")
    @ResponseBody
    public List<Speciality> findAll() {
        return iSpecialityService.getAllSpecialities();
    }

    @PutMapping("/edit")
    public void editSpeciality(@RequestBody Speciality speciality) {
        iSpecialityService.editSpeciality(speciality);
    }
}
