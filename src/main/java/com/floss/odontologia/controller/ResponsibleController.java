package com.floss.odontologia.controller;

import com.floss.odontologia.model.ResponsibleAdult;
import com.floss.odontologia.service.interfaces.IResponsibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/responsible")
public class ResponsibleController {

    @Autowired
    private IResponsibleService iResponsibleService;

    @RequestMapping("/create")
    public String createResponsible(@RequestBody ResponsibleAdult responsibleAdult){
        return iResponsibleService.createResponsible(responsibleAdult);
    }

}
