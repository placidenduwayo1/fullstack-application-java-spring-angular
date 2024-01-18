package fr.natan.cleanarchitectureemployeesservice.domain.ports.input;

import fr.natan.cleanarchitectureemployeesservice.domain.entity.Employee;
import fr.natan.cleanarchitectureemployeesservice.domain.exception_metrier.*;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.AddressModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.ProjectModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeInputService {
    List<Employee> getAllEmployees();
    Employee createEmployee(EmployeeDto employeeDto) throws EmployeeFieldsInvalidException,
            EmployeeAlreadyExistsException, EmployeeCreationErrorDueToAddressAPIException;
    Optional<AddressModel> getAddressByID(Long addressID);

    List<Employee> getEmployeeByInfo(EmployeeDto employeeDto);
    Optional<Employee> getEmployeeByID(String employeeID) throws EmployeeNotFoundException;
    Employee updateEmployee(String employeeID, EmployeeDto employeeDto) throws EmployeeNotFoundException,
            EmployeeFieldsInvalidException, EmployeeCreationErrorDueToAddressAPIException, EmployeeAlreadyExistsException;
    void deleteEmployee(String employeeID) throws EmployeeNotFoundException, EmployeeAssociatedProjectsException;
    List<Employee> getEmployeesLivingAtGivenAddress(Long addressID);
    List<ProjectModel> getProjectsAssignedToEmployee(String employeeID) throws EmployeeNotFoundException;
}
