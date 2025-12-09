package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.model.Role;

import java.util.List;

public interface IRoleService {

    //read
    public Role knowRoleByUser(String username);
    public List<Role> getListRoles();

    //update
    public String editRole(Role role);

}
