package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.service;

import fr.natan.cleanarchitectureemployeesservice.domain.entity.Employee;
import fr.natan.cleanarchitectureemployeesservice.domain.exception_metrier.EmployeeNotFoundException;
import fr.natan.cleanarchitectureemployeesservice.domain.ports.output.EmployeeOutputService;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.AddressModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.ProjectModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.services.AddressServiceProxy;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.services.ProjectServiceProxy;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeDto;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model_mapper.EmployeeMapper;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeOutputServiceImpl implements EmployeeOutputService {
    private final EmployeeRepository employeeRepository;
    private final AddressServiceProxy addressServiceProxy;
    private final ProjectServiceProxy projectServiceProxy;
    public EmployeeOutputServiceImpl(EmployeeRepository employeeRepository,
                                     @Qualifier(value = "addressserviceproxy")AddressServiceProxy addressServiceProxy,
                                     ProjectServiceProxy projectServiceProxy) {
        this.employeeRepository = employeeRepository;
        this.addressServiceProxy = addressServiceProxy;
        this.projectServiceProxy = projectServiceProxy;
    }
    @Override
    public List<Employee> getAllEmployees() {
        return utility(employeeRepository.findByOrderByHireDateDesc());
    }
    @Override
    public Employee createEmployee(Employee employee){
        EmployeeModel employeeModel = EmployeeMapper.mapClassToModel(employee);
        EmployeeModel persistedEmployee = employeeRepository.save(employeeModel);

        return EmployeeMapper.mapEmployeeModelToClass(persistedEmployee);
    }
    @Override
    public AddressModel getAddressByID(Long addressID) {
        return addressServiceProxy.getAddressById(addressID);
    }
    @Override
    public List<Employee> getEmployeeByInfo(EmployeeDto employeeDto) {
      return utility(employeeRepository.findByFirstnameAndLastnameAndAddressID(
              employeeDto.getFirstname(), employeeDto.getLastname(), employeeDto.getAddressID()));
    }
    @Override
    public Optional<Employee> getEmployeeByID(String employeeID) throws EmployeeNotFoundException {
        EmployeeModel employeeModel = employeeRepository.findById(employeeID).orElseThrow(
                EmployeeNotFoundException::new
        );
        return Optional.of(EmployeeMapper.mapEmployeeModelToClass(employeeModel));
    }
    @Override
    public Employee updateEmployee(Employee employee) {
        EmployeeModel employeeModel = EmployeeMapper.mapClassToModel(employee);
        return EmployeeMapper.mapEmployeeModelToClass(employeeRepository.save(employeeModel));
    }
    @Override
    public void deleteEmployee(Employee employee) {
        employeeRepository.delete(EmployeeMapper.mapClassToModel(employee));
    }

    @Override
    public List<Employee> getEmployeesLivingAtGivenAddress(Long addressID) {
        return utility(employeeRepository.findByAddressID(addressID));
    }

    @Override
    public List<ProjectModel> getProjectsAssignedToEmployee(String employeeID) {
        return projectServiceProxy.getProjectsAssignedToEmployee(employeeID);
    }

    private List<Employee> utility(List<EmployeeModel> models){
        return models.stream()
                .map(EmployeeMapper::mapEmployeeModelToClass)
                .toList();
    }
}
