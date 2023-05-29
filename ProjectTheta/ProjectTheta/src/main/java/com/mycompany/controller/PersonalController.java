package com.mycompany.controller;

import com.mycompany.model.entities.Personal;
import com.mycompany.model.entities.Servicios;
import com.mycompany.services.MenuService;
import com.mycompany.services.PersonalService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class PersonalController {
    private final PersonalService personalService;

    public List<Personal> getEmployees(){
        return personalService.findAll();
    }

    public void deletePersonal(Personal personal){
        personalService.delete(personal);
    }

    public void updatePersonal(Personal personal){
        personalService.update(personal);
    }

    public Personal findPersonalById(int id){
        return personalService.findById(id);
    }

    public Personal Admin(String username,String password){
        return personalService.loginAdmin(username,password);
    }

    public Personal Employee(String password){
        return personalService.loginEmployee(password);
    }
}
