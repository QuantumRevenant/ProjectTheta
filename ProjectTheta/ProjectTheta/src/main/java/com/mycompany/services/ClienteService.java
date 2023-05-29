package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Cliente;
import com.mycompany.model.entities.Personal;
import lombok.RequiredArgsConstructor;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ClienteService implements BaseService<Cliente>{
    @Override
    public List<Cliente> findAll() {
        try {
            List<Cliente> customers = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL customerList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(listGet.getInt("idCliente"));
                c.setNombre(listGet.getString("nombre"));
                c.setApellido(listGet.getString("apellido"));
                c.setDni(listGet.getString("dni"));
                c.setTelefono(listGet.getString("telefono"));
                c.setDireccion(listGet.getString("direccion"));
                customers.add(c);
            }
            Configuration.getConnectionDatabase().close();
            listGet.close();
            return customers;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*SOLO USAR SAVE CLIENTE */
    @Override
    public void save(Cliente cliente) {
        try{
            PreparedStatement createCustomer =
                    Configuration.getConnectionDatabase().prepareStatement(("{CALL createCustomer(?,?,?,?,?,?)}"));
            createCustomer.setInt(1, cliente.getIdCliente());
            createCustomer.setString(2, cliente.getNombre());
            createCustomer.setString(3, cliente.getApellido());
            createCustomer.setString(4, cliente.getDni());
            createCustomer.setString(5, cliente.getTelefono());
            createCustomer.setString(6, cliente.getDireccion());
            createCustomer.executeQuery();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Cliente cliente) {
        try {
            PreparedStatement updateCustomer =
                    Configuration.getConnectionDatabase().prepareStatement(("{CALL updateEmployee(?,?,?,?,?,?)}"));
            updateCustomer.setInt(1, cliente.getIdCliente());
            updateCustomer.setString(2, cliente.getNombre());
            updateCustomer.setString(3, cliente.getApellido());
            updateCustomer.setString(4, cliente.getDni());
            updateCustomer.setString(5, cliente.getTelefono());
            updateCustomer.setString(6, cliente.getDireccion());
            updateCustomer.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Cliente cliente) {
        try {
            PreparedStatement deleteCustomer=
                    Configuration.getConnectionDatabase().prepareStatement(("{CALL deleteCustomer(?)}"));
            deleteCustomer.setInt(1, cliente.getIdCliente());
            deleteCustomer.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Cliente findById(int id){
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM cliente WHERE idCliente = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("idCliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setDni(resultSet.getString("dni"));
                cliente.setTelefono(resultSet.getString("telefono"));
                cliente.setDireccion(resultSet.getString("direccion"));
                return cliente;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
