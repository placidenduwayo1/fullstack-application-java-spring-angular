package fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.model_mapper;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.Company;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyDto;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyModel;
import org.springframework.beans.BeanUtils;

public class CompanyMapper {
    public static Company mapToClass(CompanyModel companyModel){
        Company company = new Company();
        BeanUtils.copyProperties(companyModel, company);
        return company;
    }

    public static CompanyModel mapToModel(Company company) {
        CompanyModel companyModel = new CompanyModel();
        BeanUtils.copyProperties(company, companyModel);
        return companyModel;
    }

    public static Company dtoToClass(CompanyDto companyDto) {
        Company company = new Company();
        BeanUtils.copyProperties(companyDto, company);

        return  company;
    }

    public static CompanyDto classToDto(Company company){
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);
        return companyDto;
    }
}
