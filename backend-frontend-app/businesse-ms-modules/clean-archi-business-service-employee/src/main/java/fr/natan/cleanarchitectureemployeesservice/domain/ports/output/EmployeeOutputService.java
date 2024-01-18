package fr.natan.cleanarchitectureemployeesservice.domain.ports.output;

import fr.natan.cleanarchitectureemployeesservice.domain.entity.Employee;
import fr.natan.cleanarchitectureemployeesservice.domain.exception_metrier.EmployeeNotFoundException;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.AddressModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.ProjectModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeDto;

import java.util.List;
import java.util.Optional;

public interface EmployeeOutputService {
    List<Employee> getAllEmployees();
    Employee createEmployee(Employee employee);
    AddressModel getAddressByID(Long addressD);
    List<Employee> getEmployeeByInfo(EmployeeDto employeeDto);
    Optional<Employee> getEmployeeByID(String employeeID) throws EmployeeNotFoundException;
    Employee updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    List<Employee> getEmployeesLivingAtGivenAddress(Long addressID);
    List<ProjectModel> getProjectsAssignedToEmployee(String employeeID);
}
