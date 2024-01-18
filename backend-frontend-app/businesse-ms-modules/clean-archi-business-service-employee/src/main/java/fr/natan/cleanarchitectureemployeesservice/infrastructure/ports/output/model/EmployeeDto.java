package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model;

import fr.natan.cleanarchitectureemployeesservice.domain.entity.EmployeeState;
import fr.natan.cleanarchitectureemployeesservice.domain.entity.EmployeeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data
public class EmployeeDto {
    private String firstname;
    private String lastname;
    private EmployeeState employeeState;
    private EmployeeType employeeType;
    private Long addressID;
}
