package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model_mapper;

import fr.natan.cleanarchitectureemployeesservice.domain.entity.Employee;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeDto;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeModel;
import org.springframework.beans.BeanUtils;

public class EmployeeMapper {

    public static Employee mapEmployeeModelToClass(EmployeeModel employeeModel) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeModel, employee);

        return employee;
    }

    public static EmployeeModel mapClassToModel(Employee employee){
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employee, employeeModel);
        return employeeModel;
    }

    public static Employee mapDtoToClass(EmployeeDto employeeDto){
        Employee employee = new Employee();
       BeanUtils.copyProperties(employeeDto, employee);

        return employee;
    }
}
