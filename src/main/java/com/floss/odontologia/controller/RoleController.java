package com.floss.odontologia.controller;

import com.floss.odontologia.model.Role;
import com.floss.odontologia.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @RequestMapping("/find-all")
    public List<Role> findAllRoles(){
        return iRoleService.getListRoles();
    }

    @RequestMapping("/edit")
    public String editRole(@RequestBody Role role){
        return iRoleService.editRole(role);
    }
}
