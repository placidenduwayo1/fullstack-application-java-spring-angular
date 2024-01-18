package fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.repository;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.CompanyType;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<CompanyModel, String> {
    List<CompanyModel> findByOrderByCompanyIDAsc();
    List<CompanyModel> findByCompanyNameAndCompanyTypeAndAgency(String companyName, CompanyType companyType, String agency);
}
