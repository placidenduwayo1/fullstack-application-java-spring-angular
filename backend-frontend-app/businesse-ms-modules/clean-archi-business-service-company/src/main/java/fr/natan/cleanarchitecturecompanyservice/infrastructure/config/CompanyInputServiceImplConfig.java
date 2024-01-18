package fr.natan.cleanarchitecturecompanyservice.infrastructure.config;

import fr.natan.cleanarchitecturecompanyservice.domain.ports.output.CompanyOutputService;
import fr.natan.cleanarchitecturecompanyservice.domain.usecase.CompanyInputServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CompanyInputServiceImplConfig {
    @Bean
    public CompanyInputServiceImpl companyInputService(@Autowired CompanyOutputService companyOutputService){
        return new CompanyInputServiceImpl(companyOutputService);
    }
}
