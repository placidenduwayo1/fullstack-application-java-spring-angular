package fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.service;

import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.entity.model.EmployeeModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "clean-archi-bs-ms-employee")
public interface EmployeeService {
    @GetMapping(value = "/employees/addresses/{addressID}")
    List<EmployeeModel> getEmployeesLivingAtGivenAddress(@PathVariable(name = "addressID") Long addressID);
}
