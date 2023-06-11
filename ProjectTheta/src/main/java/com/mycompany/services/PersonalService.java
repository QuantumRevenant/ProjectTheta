package com.mycompany.services;

import com.mycompany.database.Configuration;
import com.mycompany.model.entities.Cliente;
import com.mycompany.model.entities.Personal;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.sql.*;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PersonalService implements BaseService<Personal> {

    @Override
    public List<Personal> findAll() {
        try {
            List<Personal> employees = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL employeesList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                Personal p = new Personal();
                p.setIdPersonal(listGet.getInt("idPersonal"));
                p.setNombre(listGet.getString("nombre"));
                p.setApellidos(listGet.getString("apellidos"));
                p.setTelefono(listGet.getString("telefono"));
                p.setUsuario(listGet.getString("usuario"));
                p.setPassword(listGet.getString("password"));
                p.setHoraInicio(listGet.getString("horaInicio"));
                p.setHoraFin(listGet.getString("horaFin"));
                p.setDiaDescanso(listGet.getString("diaDescanso"));
                p.setNombreCargo(Personal.CARGOS.valueOf(listGet.getString("nombreCargo")));
                employees.add(p);
            }
            Configuration.getConnectionDatabase().close();
            listGet.close();
            return employees;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Personal personal) {
        try {
            CallableStatement createCustomer
                    = Configuration.getConnectionDatabase().prepareCall(("{CALL createEmployee(?,?,?,?,?,?,?,?,?)}"));
            createCustomer.setString(1, personal.getNombre());
            createCustomer.setString(2, personal.getApellidos());
            createCustomer.setString(3, personal.getTelefono());
            if (personal.getNombreCargo() == Personal.CARGOS.ADMIN) {
                createCustomer.setString(4, personal.getUsuario());
            } else {
                createCustomer.setNull(4, 0);
            }
            createCustomer.setString(5, personal.getPassword());
            createCustomer.setString(6, personal.getHoraInicio());
            createCustomer.setString(7, personal.getHoraFin());
            createCustomer.setString(8, personal.getDiaDescanso());
            createCustomer.setString(9, personal.getNombreCargo().toString());
            createCustomer.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Personal personal) {
        try {
            PreparedStatement updateEmployee
                    = Configuration.getConnectionDatabase().prepareStatement(("{CALL updateEmployee(?,?,?,?,?,?,?,?,?,?)}"));
            updateEmployee.setInt(1, personal.getIdPersonal());
            updateEmployee.setString(2, personal.getNombre());
            updateEmployee.setString(3, personal.getApellidos());
            updateEmployee.setString(4, personal.getTelefono());
            if (personal.getNombreCargo() == Personal.CARGOS.ADMIN) {
                updateEmployee.setString(5, personal.getUsuario());
            } else {
                updateEmployee.setNull(5, 0);
            }
            updateEmployee.setString(6, personal.getPassword());
            updateEmployee.setString(7, personal.getHoraInicio());
            updateEmployee.setString(8, personal.getHoraFin());
            updateEmployee.setString(9, personal.getDiaDescanso());
            updateEmployee.setString(10, personal.getNombreCargo().toString());
            updateEmployee.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Personal personal) {
        try {
            PreparedStatement deleteEmployee
                    = Configuration.getConnectionDatabase().prepareStatement(("{CALL deleteEmployeeById(?)}"));
            deleteEmployee.setInt(1, personal.getIdPersonal());
            deleteEmployee.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Personal findById(int id) {
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT * FROM personal WHERE idPersonal = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Personal personal = new Personal();
                personal.setIdPersonal(resultSet.getInt("idPersonal"));
                personal.setNombre(resultSet.getString("nombre"));
                personal.setApellidos(resultSet.getString("apellidos"));
                personal.setTelefono(resultSet.getString("telefono"));
                personal.setUsuario(resultSet.getString("usuario"));
                personal.setPassword(resultSet.getString("password"));
                personal.setHoraInicio(resultSet.getString("horaInicio"));
                personal.setHoraFin(resultSet.getString("horaFin"));
                personal.setHoraFin(resultSet.getString("horaFin"));
                personal.setDiaDescanso(resultSet.getString("diaDescanso"));
                personal.setNombreCargo(Personal.CARGOS.valueOf(resultSet.getString("nombreCargo")));

                return personal;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Personal loginAdmin(String username, String password) {
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT idPersonal, nombre, apellidos FROM personal WHERE usuario = ? AND password = ? AND nombreCargo = 'ADMIN'");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Personal personal = new Personal();
                personal.setIdPersonal(resultSet.getInt("idPersonal"));
                personal.setNombre(resultSet.getString("nombre"));
                personal.setApellidos(resultSet.getString("apellidos"));
                return personal;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Personal loginEmployee(String password) {
        try {
            PreparedStatement statement = Configuration.getConnectionDatabase().prepareStatement("SELECT idPersonal, nombre, apellidos FROM personal WHERE  password = ? AND (nombreCargo != 'ADMIN' OR nombreCargo='EMPLEADO')");
            statement.setString(1, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Personal personal = new Personal();
                personal.setIdPersonal(resultSet.getInt("idPersonal"));
                personal.setNombre(resultSet.getString("nombre"));
                personal.setApellidos(resultSet.getString("apellidos"));
                return personal;
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
