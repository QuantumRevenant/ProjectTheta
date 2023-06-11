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
    
    public byte datosExist(String dni, String telefono, int id){
        List<Cliente> lstClientes = getEmployees();
        for(Cliente c : lstClientes){
            if(c.getIdCliente() != id){
                if(c.getDni().equalsIgnoreCase(dni)){ return 1; }
                if(c.getTelefono().equalsIgnoreCase(telefono)){ return 2; }
            }
        }
        return 0;
    }
}
