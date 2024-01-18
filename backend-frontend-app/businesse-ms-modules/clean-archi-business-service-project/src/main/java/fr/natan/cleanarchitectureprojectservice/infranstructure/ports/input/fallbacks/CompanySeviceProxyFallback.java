package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.fallbacks;

import fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers.ExceptionWarnMsg;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.CompanyModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.enums.CompanyType;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services.CompanyServiceProxy;
import org.springframework.stereotype.Component;

@Component
public class CompanySeviceProxyFallback implements CompanyServiceProxy {
    @Override
    public CompanyModel getCompanyByID(String companyID) {
        CompanyModel emptyCompany = new CompanyModel();
        emptyCompany.setCompanyID(ExceptionWarnMsg.COMPANY_API_ERROR.toString());
        emptyCompany.setCompanyName(ExceptionWarnMsg.COMPANY_API_ERROR.toString());
        emptyCompany.setCompanyType(CompanyType.COMPANY_API_ERROR);
        return emptyCompany;
    }
}
