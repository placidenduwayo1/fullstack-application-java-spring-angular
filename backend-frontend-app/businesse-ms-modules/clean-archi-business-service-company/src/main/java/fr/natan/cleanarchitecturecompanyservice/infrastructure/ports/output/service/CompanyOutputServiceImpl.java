package fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.service;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.Company;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyNotFoundException;
import fr.natan.cleanarchitecturecompanyservice.domain.ports.output.CompanyOutputService;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.feignclient.model.ProjectModel;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.feignclient.service.ProjectService;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.model_mapper.CompanyMapper;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyDto;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyModel;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyOutputServiceImpl implements CompanyOutputService {
    private final CompanyRepository companyRepository;
    private final ProjectService projectService;

    public CompanyOutputServiceImpl(CompanyRepository companyRepository, ProjectService projectService) {
        this.companyRepository = companyRepository;
        this.projectService = projectService;
    }

    @Override
    public List<Company> getAllCompanies() {
        List<CompanyModel> companyModels = companyRepository.findByOrderByCompanyIDAsc();
        return companyModels.stream()
                .map(CompanyMapper::mapToClass)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Company> getCompanyByID(String companyID) throws CompanyNotFoundException {
        CompanyModel companyModel = companyRepository.findById(companyID).orElseThrow(
                CompanyNotFoundException::new
        );
        return Optional.of(CompanyMapper.mapToClass(companyModel));
    }

    @Override
    public List<Company> getCompanyByInfos(CompanyDto companyDto) {
        List<CompanyModel> companyModels = companyRepository.findByCompanyNameAndCompanyTypeAndAgency(
                companyDto.getCompanyName(), companyDto.getCompanyType(), companyDto.getAgency()
        );
        return companyModels.stream()
                .map(CompanyMapper::mapToClass)
                .collect(Collectors.toList());
    }

    @Override
    public Company createCompany(Company company)  {
        CompanyModel mappedModel = CompanyMapper.mapToModel(company);
        CompanyModel savedModel = companyRepository.save(mappedModel);
        return CompanyMapper.mapToClass(savedModel);
    }

    @Override
    public Company updateCompany(Company company) {
        CompanyModel mappedCompany = CompanyMapper.mapToModel(company);
        CompanyModel savedCompany= companyRepository.save(mappedCompany);

        return CompanyMapper.mapToClass(savedCompany);
    }

    @Override
    public void deleteCompany(Company company) {
        companyRepository.delete(CompanyMapper.mapToModel(company));
    }

    @Override
    public List<ProjectModel> getProjectsAssignedToCompany(String companyID) {
        return projectService.getProjectsAssignedCompany(companyID);
    }
}
