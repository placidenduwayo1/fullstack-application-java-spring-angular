package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.controller;

import fr.natan.cleanarchitectureemployeesservice.domain.entity.Employee;
import fr.natan.cleanarchitectureemployeesservice.domain.exception_metrier.*;
import fr.natan.cleanarchitectureemployeesservice.domain.ports.input.EmployeeInputService;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.ProjectModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.services.AddressServiceProxy;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeInputService employeeInputService;
    private final AddressServiceProxy addressServiceProxy;

    public EmployeeController(EmployeeInputService employeeInputService,
                              @Qualifier(value = "addressserviceproxy")AddressServiceProxy addressServiceProxy) {
        this.employeeInputService = employeeInputService;
        this.addressServiceProxy = addressServiceProxy;
    }


    @GetMapping(value = "/employees")
    public List<Employee> getEmployees(){
        List<Employee> employees = employeeInputService.getAllEmployees();
        employees.forEach(employee -> employee.setAddress(addressServiceProxy.getAddressById(employee.getAddressID())));
        return employees;
    }

    @PostMapping(value = "/employees")
    public Employee createEmployee(@RequestBody EmployeeDto employeeDto) throws EmployeeFieldsInvalidException,
            EmployeeAlreadyExistsException, EmployeeCreationErrorDueToAddressAPIException {
        return employeeInputService.createEmployee(employeeDto);
    }

    @PutMapping(value = "/employees/{employeeID}")
    public Employee updateEmployee(@PathVariable(name = "employeeID") String employeeID, @RequestBody EmployeeDto employeeDto)
            throws EmployeeNotFoundException, EmployeeFieldsInvalidException, EmployeeCreationErrorDueToAddressAPIException,
            EmployeeAlreadyExistsException {
        Employee employee = employeeInputService.updateEmployee(employeeID, employeeDto);
        employee.setAddress(addressServiceProxy.getAddressById(employee.getAddressID()));
        return employee;
    }

    @GetMapping(value = "/employees/{employeeID}")
    public Employee getEmployeeByID(@PathVariable(name = "employeeID") String employeeID) throws EmployeeNotFoundException {
        Employee employee = employeeInputService.getEmployeeByID(employeeID).orElseThrow(EmployeeNotFoundException::new);
        employee.setAddress(addressServiceProxy.getAddressById(employee.getAddressID()));
        return employee;
    }

    @DeleteMapping(value = "/employees/{employeeID}")
    public void deleteEmployee(@PathVariable(name = "employeeID") String employeeID) throws EmployeeNotFoundException,
            EmployeeAssociatedProjectsException {
        employeeInputService.deleteEmployee(employeeID);
    }

    @GetMapping(value = "/employees/addresses/{addressID}")
    public List<Employee> getEmployeesLivingGivenAddress(@PathVariable(name = "addressID") Long addressID){
        List<Employee> employees = employeeInputService.getEmployeesLivingAtGivenAddress(addressID);
        employees.forEach(employee -> employee.setAddress(addressServiceProxy.getAddressById(employee.getAddressID())));

        return employees;
    }
    @GetMapping(value = "/projects/employees/{employeeID}")
    public List<ProjectModel> getProjectsAssignedToEmployee(@PathVariable(name = "employeeID") String employeeID) throws EmployeeNotFoundException {
        return employeeInputService.getProjectsAssignedToEmployee(employeeID);
    }
}
