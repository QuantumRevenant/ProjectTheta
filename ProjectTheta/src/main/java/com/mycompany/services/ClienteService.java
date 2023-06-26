package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Cliente;
import java.sql.*;
import java.util.*;

public class ClienteService implements BaseService<Cliente> {

    private Configuration configuration = Configuration.getConf();

    @Override
    public List<Cliente> findAll() {
        try {
            List<Cliente> customers = new ArrayList<>();
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement caller = conn.prepareCall("{CALL customerList()}");
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
            
            listGet.close();
            configuration.releaseConnection(conn);
            return customers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /*SOLO USAR SAVE CLIENTE */
    @Override
    public void save(Cliente cliente) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement createCustomer = conn.prepareCall(("{CALL createCustomer(?,?,?,?,?)}"));
            createCustomer.setString(1, cliente.getNombre());
            createCustomer.setString(2, cliente.getApellido());
            createCustomer.setString(3, cliente.getDni());
            createCustomer.setString(4, cliente.getTelefono());
            createCustomer.setString(5, cliente.getDireccion());
            createCustomer.executeQuery();
            configuration.releaseConnection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Cliente cliente) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement updateCustomer = conn.prepareCall(("{CALL updateCustomer(?,?,?,?,?,?)}"));
            updateCustomer.setInt(1, cliente.getIdCliente());
            updateCustomer.setString(2, cliente.getNombre());
            updateCustomer.setString(3, cliente.getApellido());
            updateCustomer.setString(4, cliente.getDni());
            updateCustomer.setString(5, cliente.getTelefono());
            updateCustomer.setString(6, cliente.getDireccion());
            updateCustomer.executeUpdate();
            configuration.releaseConnection(conn);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Cliente cliente) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            CallableStatement deleteCustomer = conn.prepareCall(("{CALL deleteCustomer(?)}"));
            deleteCustomer.setInt(1, cliente.getIdCliente());
            deleteCustomer.executeUpdate();
            configuration.releaseConnection(conn);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Cliente findById(int id) {
        try {
            Connection conn = configuration.getConnectionDatabase();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM reservation.cliente WHERE idCliente = ?");
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
                resultSet.close();
                statement.close();
                configuration.releaseConnection(conn);
                return cliente;
            }
            resultSet.close();
            statement.close();
            configuration.releaseConnection(conn);
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
