package fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.CompanyType;

public class CompanyDto {
    private String companyName;
    private CompanyType companyType;
    private String agency;

    public CompanyDto() {
    }

    public CompanyDto(String companyName, CompanyType companyType, String agency) {
        this.companyName = companyName;
        this.companyType = companyType;
        this.agency = agency;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }
}
