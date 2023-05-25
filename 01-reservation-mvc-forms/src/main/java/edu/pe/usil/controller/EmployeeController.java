package edu.pe.usil.controller;

import edu.pe.usil.model.Employee;
import edu.pe.usil.service.EmployeeService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class EmployeeController {
    EmployeeService employeeService;
    public List<Employee> getEmployees(){
        return employeeService.findAll();
    }

    public void addEmployee(Employee employee){
        employeeService.save(employee);
    }

    public void removeEmployee(Employee employee){
        employeeService.delete(employee);
    }

    public void updateEmployee(Employee employee){
        employeeService.update(employee);
    }

    public Boolean verifyEmployee(String username, String password){
        return employeeService.verifyCredentials(username, password);
    }
}
