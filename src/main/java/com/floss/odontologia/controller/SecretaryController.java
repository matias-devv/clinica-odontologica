package com.floss.odontologia.controller;

import com.floss.odontologia.model.Secretary;
import com.floss.odontologia.service.interfaces.ISecretaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secretary")
public class SecretaryController {

    @Autowired
    private ISecretaryService iSecretaryService;

    @RequestMapping("/create")
    public String createSecretary(@RequestBody Secretary secretary) {
        return iSecretaryService.createSecretary(secretary);
    }

    @RequestMapping("/edit")
    public String editSecretary(@RequestBody Secretary secretary) {
        return iSecretaryService.editSecretary(secretary);
    }
}
