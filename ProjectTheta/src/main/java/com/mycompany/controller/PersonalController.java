package com.mycompany.controller;

import com.mycompany.model.entities.Personal;
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
    
    public Personal findPersonalByPassword(String password){
        for(Personal p:getEmployees()){
            if(p.getPassword().equalsIgnoreCase(password)){ return p; }
        }
        return null;
    }
    
    public boolean usuarioExist(String usuario){
        for(Personal c:getEmployees()){
            if( c.getUsuario().equalsIgnoreCase(usuario)){ return true; }
        }
        return false;
    }
    
    public boolean telefonoExist(String telefono){
        for(Personal c:getEmployees()){
            if( c.getTelefono().equalsIgnoreCase(telefono)){ return true; }
        }
        return false;
    }
    
    public boolean contrasenaExist(String contrasena){
        for(Personal c:getEmployees()){
            if( c.getPassword().equalsIgnoreCase(contrasena)){ return true; }
        }
        return false;
    }
}
