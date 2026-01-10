package com.floss.odontologia.service.impl;

import com.floss.odontologia.security.config.Hashed;
import com.floss.odontologia.dto.request.UserDTO;
import com.floss.odontologia.model.*;
import com.floss.odontologia.repository.IUserRepository;
import com.floss.odontologia.service.interfaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IDentistService iDentistService;

    @Autowired
    private IRoleService iRoleService;

    @Autowired
    private ISpecialityService iSpecialityService;

    @Autowired
    private ISecretaryService iSecretaryService;

    @Override
    public String createUser(UserDTO userDTO) {

        System.out.println( "nombre: " + userDTO.getName());
        boolean ok = this.avoidDuplicateUsers(userDTO);
        if (!ok){
           return "The user has already been created";
        }
        else{
                if ( userDTO.getRole().equalsIgnoreCase("odontologo") ) {
                    return createDentistUser(userDTO);
                }
                else{
                   return createSecretaryUser(userDTO);
                }
        }
    }

    private String createDentistUser(UserDTO userDTO) {

        //I catch the object "Role" and "Speciality"
        Role role = iRoleService.findRoleByName(userDTO.getRole());
        Speciality speciality = iSpecialityService.getSpecialityByName(userDTO.getSpeciality());

        //I need to persist the user and the dentist first, then establish relations
        User user = this.setAttributesDtoToUser(userDTO, role);
        iUserRepository.save(user);

        Dentist dentist = this.setAttributesDtoToDentist(userDTO, speciality);
        iDentistService.createDentist(dentist);

        //With this I establish the relations
        this.UpdateUserDentist(user, dentist);

        return "The dentist was saved successfully";
    }

    private String createSecretaryUser(UserDTO userDTO) {

        //I catch the object "Role"
        Role role = iRoleService.findRoleByName(userDTO.getRole());

        //I need to persist the user and the secretary first, then establish relations
        User user = this.setAttributesDtoToUser(userDTO, role);
        iUserRepository.save(user);

        Secretary secretary = this.setAttributesDtoToSecretary(userDTO);
        iSecretaryService.createSecretary(secretary);

        //With this I establish the relations
        this.UpdateUserSecretary(user, secretary);

        return "The secretary was saved successfully";
    }

    private boolean avoidDuplicateUsers(UserDTO userDTO) {

        List<User> listUsers = iUserRepository.findAll();

        if (listUsers != null) {
            for (User user : listUsers) {
                //only if the username is the same -> return false
                if ( user.getUsername().equals(userDTO.getUsername())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String validateUser(User user) {

        boolean match = false;

        //verify if the user exist already
        User userFound = this.getUserByUsername(user.getUsername());

        if(userFound != null){

            //compare the "password" that I received and the "password"(encrypt) in the DB for validation
            match = Hashed.matches( user.getPassword(), userFound.getPassword());
            if (match) {
                return "You have complete access to the sistem";
            }
            return "The password do not match";
        }
        return "The user does not exist";

    }

    private User getUserByUsername(String username) {
        List<User> list = iUserRepository.findAll();
        for (User user : list) {
            if ( user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private User setAttributesDtoToUser(UserDTO userDTO, Role role) {
        User user = new User();
        //attributes
        user.setUsername(userDTO.getUsername());
        user.setPassword( Hashed.hashPassword(userDTO.getPassword()) );
        //relations
        user.setRole(role);
        return user;
    }

    private Dentist setAttributesDtoToDentist(UserDTO userDTO, Speciality speciality) {
        Dentist dentist = new Dentist();
        //attributes
        dentist.setName( userDTO.getName() );
        dentist.setSurname(userDTO.getSurname());
        dentist.setDni(userDTO.getDni());
        //relations
        dentist.setSpeciality(speciality);
        return dentist;
    }

    private void UpdateUserDentist(User user, Dentist dentist) {
        user.setDentist(dentist);
        dentist.setUser(user);
        //update
        iDentistService.createDentist(dentist);
        iUserRepository.save(user);
    }


    private Secretary setAttributesDtoToSecretary(UserDTO userDTO) {
        Secretary secretary = new Secretary();
        secretary.setName(userDTO.getName());
        secretary.setSurname(userDTO.getSurname());
        secretary.setDni(userDTO.getDni());
        return secretary;
    }

    private void UpdateUserSecretary(User user, Secretary secretary) {
        secretary.setUser(user);
        user.setSecretary(secretary);
        iSecretaryService.createSecretary(secretary);
        iUserRepository.save(user);
    }
}
