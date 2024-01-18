package fr.natan.cleanarchitecturecompanyservice.domain.ports.output;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.Company;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyNotFoundException;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.feignclient.model.ProjectModel;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyOutputService {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyByID(String companyID) throws CompanyNotFoundException;
    List<Company> getCompanyByInfos(CompanyDto companyDto);
    Company createCompany(Company company);
    Company updateCompany(Company company);
    void deleteCompany(Company company);
    List<ProjectModel> getProjectsAssignedToCompany(String companyID);
}
