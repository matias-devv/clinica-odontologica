package com.floss.odontologia.service.interfaces;

import com.floss.odontologia.dto.response.RoleDTO;
import com.floss.odontologia.model.Role;

import java.util.List;

public interface IRoleService {

    //read
    public Role knowRoleByUser(String username);

    public List<RoleDTO> getListRoles();

    //update
    public String editRole(Role role);

    public Role findRoleByName(String role);
}
