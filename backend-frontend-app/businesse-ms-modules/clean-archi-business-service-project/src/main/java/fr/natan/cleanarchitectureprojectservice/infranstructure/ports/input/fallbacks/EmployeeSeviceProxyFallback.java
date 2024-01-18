package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.fallbacks;

import fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers.ExceptionWarnMsg;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.EmployeeModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.enums.EmployeeState;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.enums.EmployeeType;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services.EmployeeServiceProxy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class EmployeeSeviceProxyFallback implements EmployeeServiceProxy {
    @Override
    public EmployeeModel getEmployeeByID(String employeeID) {
        EmployeeModel emptyEmployee = new EmployeeModel();
        emptyEmployee.setEmployeeID(ExceptionWarnMsg.EMPLOYEE_API_ERROR.toString());
        emptyEmployee.setFirstname(ExceptionWarnMsg.EMPLOYEE_API_ERROR.toString());
        emptyEmployee.setLastname(ExceptionWarnMsg.EMPLOYEE_API_ERROR.toString());
        emptyEmployee.setEmail(ExceptionWarnMsg.EMPLOYEE_API_ERROR.toString());
        emptyEmployee.setHireDate(LocalDateTime.now());
        emptyEmployee.setEmployeeType(EmployeeType.EMPLOYEE_API_ERROR);
        emptyEmployee.setEmployeeState(EmployeeState.EMPLOYEE_API_ERROR);
        return emptyEmployee;
    }
}
