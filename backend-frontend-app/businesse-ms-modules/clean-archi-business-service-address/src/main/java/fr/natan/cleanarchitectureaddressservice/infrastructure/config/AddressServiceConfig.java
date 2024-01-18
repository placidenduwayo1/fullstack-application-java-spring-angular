package fr.natan.cleanarchitectureaddressservice.infrastructure.config;

import fr.natan.cleanarchitectureaddressservice.domain.ports.output.AddressOutputService;
import fr.natan.cleanarchitectureaddressservice.domain.usecase.AddressInputServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AddressServiceConfig {

    @Bean
    public AddressInputServiceImpl configureAddressUseCase(@Autowired AddressOutputService addressOutputService){
        return new AddressInputServiceImpl(addressOutputService);
    }
}
