package com.floss.odontologia.service.impl;

import com.floss.odontologia.dto.response.RoleDTO;
import com.floss.odontologia.model.Role;
import com.floss.odontologia.model.User;
import com.floss.odontologia.repository.IRoleRepository;
import com.floss.odontologia.repository.IUserRepository;
import com.floss.odontologia.service.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository iRoleRepository;

    @Autowired
    private IUserRepository iUserRepository;

    @Override
    public Role knowRoleByUser(String username) {

        //I catch the list of users and the roles
        List<User> listUsers = iUserRepository.findAll();
        List<Role> listRoles = iRoleRepository.findAll();
        Role role = new Role();

        for (User usu : listUsers) {

            if ( usu.getUsername().equals(username) ) {

                for (Role ro : listRoles) {

                    //If the role of the current user is the same as the current role -> return role
                    if ( usu.getRole().getName().equals(ro.getName()) ) {
                        role = usu.getRole();
                        return role;
                    }
                }
            }
        }
        return role;
    }

    @Override
    public List<RoleDTO> getListRoles() {
        return this.convertEntityToDTO( iRoleRepository.findAll());
    }

    private List<RoleDTO> convertEntityToDTO(List<Role> all) {
        List<RoleDTO> listRolesDto = new ArrayList<>();

        for(Role role : all){
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setId_role(role.getId());
            roleDTO.setRoleName(role.getName());
            listRolesDto.add(roleDTO);
        }
        return listRolesDto;
    }

    @Override
    public String editRole(Role role) {
        iRoleRepository.save(role);
        return "The role was edited succesfully";
    }

    @Override
    public Role findRoleByName(String role) {
        List<Role> list = iRoleRepository.findAll();
        if(list.isEmpty()){
            return null;
        }
        for(Role roleEntity : list){
            if(  roleEntity.getName().equals(role)){
                return roleEntity;
            }
        }
        return null;
    }
}
