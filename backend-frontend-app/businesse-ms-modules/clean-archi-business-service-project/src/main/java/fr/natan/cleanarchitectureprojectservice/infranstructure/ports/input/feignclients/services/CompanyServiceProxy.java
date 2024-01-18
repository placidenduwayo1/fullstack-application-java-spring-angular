package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services;

import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.fallbacks.CompanySeviceProxyFallback;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.CompanyModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clean-archi-bs-ms-company", fallback = CompanySeviceProxyFallback.class)
@Qualifier(value = "companyserviceproxy")
public interface CompanyServiceProxy {
    @GetMapping(value = "/companies/{companyID}")
    CompanyModel getCompanyByID(@PathVariable(name = "companyID") String companyID);
}

