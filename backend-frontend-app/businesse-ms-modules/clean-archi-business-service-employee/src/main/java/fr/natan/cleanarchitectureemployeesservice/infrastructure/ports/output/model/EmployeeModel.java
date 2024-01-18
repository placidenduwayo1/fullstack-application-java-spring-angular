package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model;

import fr.natan.cleanarchitectureemployeesservice.domain.entity.EmployeeState;
import fr.natan.cleanarchitectureemployeesservice.domain.entity.EmployeeType;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.AddressModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@AllArgsConstructor @NoArgsConstructor @Data
public class EmployeeModel {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String employeeID;
    private String firstname;
    private String lastname;
    private String email;
    private LocalDateTime hireDate;
    @Enumerated(EnumType.STRING)
    private EmployeeState employeeState;
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    private Long addressID;
    @Transient
    private AddressModel address;
}
