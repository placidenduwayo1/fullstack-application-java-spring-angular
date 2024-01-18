package fr.natan.cleanarchitectureemployeesservice.infrastructure.config;

import fr.natan.cleanarchitectureemployeesservice.domain.ports.output.EmployeeOutputService;
import fr.natan.cleanarchitectureemployeesservice.domain.usecase.EmployeeInputServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConfig {

    @Bean
    public EmployeeInputServiceImpl employeeUseCase(@Autowired EmployeeOutputService employeeOutputService){
        return new EmployeeInputServiceImpl(employeeOutputService);
    }
}
