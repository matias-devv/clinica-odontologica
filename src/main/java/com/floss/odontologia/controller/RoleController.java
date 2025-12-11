package com.floss.odontologia.controller;

import com.floss.odontologia.model.Role;
import com.floss.odontologia.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService iRoleService;

    @GetMapping("/find-all")
    @ResponseBody
    public List<Role> findAllRoles(){
        return iRoleService.getListRoles();
    }

    @PutMapping("/edit")
    public String editRole(@RequestBody Role role){
        return iRoleService.editRole(role);
    }
}
