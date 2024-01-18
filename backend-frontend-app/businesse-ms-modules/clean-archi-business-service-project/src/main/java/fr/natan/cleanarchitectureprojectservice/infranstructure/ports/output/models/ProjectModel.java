package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models;

import fr.natan.cleanarchitectureprojectservice.domain.entity.Priority;
import fr.natan.cleanarchitectureprojectservice.domain.entity.ProjectState;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.CompanyModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.EmployeeModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
@NoArgsConstructor @AllArgsConstructor @Data
@Entity @Table(name = "projects")
public class ProjectModel {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String projectID;
    private String projectName;
    private String description;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Enumerated(EnumType.STRING)
    private ProjectState projectState;
    private LocalDateTime createdDate;
    private String employeeID;
    @Transient
    private EmployeeModel employee;
    private String companyID;
    @Transient
    private CompanyModel company;
}
