package fr.natan.cleanarchitecturecompanyservice.domain.ports.input;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.Company;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyAlreadyExistsException;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyAssociedProjectsException;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyFieldsEmptyException;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyNotFoundException;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.feignclient.model.ProjectModel;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyDto;

import java.util.List;
import java.util.Optional;

public interface CompanyInputSevice {
    List<Company> getAllCompanies();
    Optional<Company> getCompanyByID(String companyID) throws CompanyNotFoundException;
    List<Company> getCompanyByInfos(CompanyDto companyDto);
    Company createCompany(CompanyDto companyDto) throws CompanyAlreadyExistsException, CompanyFieldsEmptyException;
    Company updateCompany(String companyID, CompanyDto companyDto) throws CompanyNotFoundException,
            CompanyFieldsEmptyException, CompanyAlreadyExistsException;
    void deleteCompany(String companyID) throws CompanyNotFoundException, CompanyAssociedProjectsException;
    List<ProjectModel> getProjectsAssignedToCompany(String companyID) throws CompanyNotFoundException;
}
