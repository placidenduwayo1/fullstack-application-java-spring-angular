package fr.natan.cleanarchitecturecompanyservice.domain.usecase;

import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyDto;

@SuppressWarnings("BooleanMethodIsAlwaysInverted")
public class CompanyValidation {

    public static boolean areValidCompanyTextFields(CompanyDto companyDto) {
        if (companyDto.getCompanyName().isBlank()
                || companyDto.getAgency().isBlank()) {
            return false;
        }
        return true;
    }

    public static void companyFormatter(CompanyDto companyDto) {
        companyDto.setCompanyName(companyDto.getCompanyName().strip());
        companyDto.setAgency(companyDto.getAgency().strip());
    }
}
