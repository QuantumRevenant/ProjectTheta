package com.mycompany.controller;

import com.mycompany.model.entities.Cliente;
import com.mycompany.services.ClienteService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;
    public List<Cliente> getEmployees(){
        return clienteService.findAll();
    }

    public void addCustomer(Cliente cliente) {
        clienteService.save(cliente);
    }

    public void deleteCustomer(Cliente cliente){
        clienteService.delete(cliente);
    }

    public void updateCustomer(Cliente cliente){
        clienteService.update(cliente);
    }

    public Cliente findCustomerById(int id){
        return clienteService.findById(id);
    }
    
    public boolean dniExist(String dni){
        for(Cliente c:getEmployees()){
            if( c.getDni().equalsIgnoreCase(dni)){ return true; }
        }
        return false;
    }
    
    public boolean telefonoExist(String telefono){
        for(Cliente c:getEmployees()){
            if( c.getTelefono().equalsIgnoreCase(telefono)){ return true; }
        }
        return false;
    }
}