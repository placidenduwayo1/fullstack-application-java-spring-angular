package fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.CompanyType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@AllArgsConstructor @NoArgsConstructor @Data
public class CompanyModel {
    @Id
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String companyID;
    private String companyName;
    private String agency;
    private CompanyType companyType;
    private LocalDateTime connectedDate;
}
