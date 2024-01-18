package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services;

import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.fallbacks.EmployeeSeviceProxyFallback;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.EmployeeModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clean-archi-bs-ms-employee", fallback = EmployeeSeviceProxyFallback.class)
@Qualifier(value = "employeeserviceproxy")
public interface EmployeeServiceProxy {
    @GetMapping(value = "/employees/{employeeID}")
    EmployeeModel getEmployeeByID(@PathVariable(name = "employeeID") String employeeID);
}
