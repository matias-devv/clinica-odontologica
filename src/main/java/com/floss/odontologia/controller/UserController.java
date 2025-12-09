package com.floss.odontologia.controller;

import com.floss.odontologia.model.User;
import com.floss.odontologia.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping("/create")
    public String createUser(@RequestBody User user){
        return iUserService.createUser(user);
    }

}
