package edu.pe.usil.service;

import edu.pe.usil.db.Configuration;
import edu.pe.usil.model.Employee;

import java.sql.*;
import java.util.*;

public class EmployeeService implements BaseService<Employee, Integer> {
    @Override
    public List<Employee> findAll() {
        try {
            List<Employee> employees = new ArrayList<>();
            CallableStatement caller = Configuration.getConnectionDatabase().prepareCall("{CALL employeesList()}");
            ResultSet listGet = caller.executeQuery();
            while (listGet.next()) {
                Employee e = new Employee();
                e.setEmployee_id(listGet.getInt("employee_id"));
                e.setName(listGet.getString("name"));
                e.setLastname(listGet.getString("lastname"));
                e.setUsername(listGet.getString("username"));
                String pass = listGet.getString("password");
                e.setPassword(pass);
                employees.add(e);
            }
            Configuration.getConnectionDatabase().close();
            listGet.close();
            return employees;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void save(Employee employee) {
        try{
            PreparedStatement createEmployee =
                    Configuration.getConnectionDatabase().prepareStatement(("{CALL createEmployee(?,?,?,?,?)}"));
            createEmployee.setInt(1, employee.getEmployee_id());
            createEmployee.setString(2, employee.getName());
            createEmployee.setString(3, employee.getLastname());
            createEmployee.setString(4, employee.getUsername());
            createEmployee.setString(5, employee.getPassword());
            createEmployee.executeQuery();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Employee employee) {
        try {
            PreparedStatement updateEmployee =
                    Configuration.getConnectionDatabase().prepareStatement(("{CALL updateUserAndPassEmployee(?,?,?)}"));
            updateEmployee.setInt(1, employee.getEmployee_id());
           /*updateEmployee.setString(2, employee.getName());
            updateEmployee.setString(3, employee.getLastname());*/
            updateEmployee.setString(4, employee.getUsername());  // VERIFICAR SOLO ACTUALIZAR 2 CAMPOS
            updateEmployee.setString(5, employee.getPassword()); //VERIFICAR
            updateEmployee.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Employee employee) {
        try {
            PreparedStatement deleteEmployee =
                    Configuration.getConnectionDatabase().prepareStatement(("{CALL deleteEmployeeById(?)}"));
            deleteEmployee.setInt(1, employee.getEmployee_id());
            deleteEmployee.executeUpdate();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Boolean verifyCredentials(String user , String password){
        try{
            PreparedStatement verifyCredentials =
                    Configuration.getConnectionDatabase().prepareStatement(("{CALL verifyCredentials(?,?)}"));
            verifyCredentials.setString(4,"username");
            verifyCredentials.setString(5,"password");
            verifyCredentials.executeQuery();
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
