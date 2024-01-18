package fr.natan.cleanarchitectureprojectservice.infranstructure.config;

import fr.natan.cleanarchitectureprojectservice.domain.ports.output.ProjectOutputService;
import fr.natan.cleanarchitectureprojectservice.domain.usecase.ProjectInputServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProjectInputServiceImplConfig {
    @Bean
    public ProjectInputServiceImpl projectInputService(@Autowired ProjectOutputService projectOutputService){
        return new ProjectInputServiceImpl(projectOutputService);
    }
}
