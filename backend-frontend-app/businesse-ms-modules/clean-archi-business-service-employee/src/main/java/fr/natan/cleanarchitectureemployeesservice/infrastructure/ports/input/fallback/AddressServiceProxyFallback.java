package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.fallback;

import fr.natan.cleanarchitectureemployeesservice.domain.exception_metrier.ExceptionWarnMsg;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.AddressModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.services.AddressServiceProxy;
import org.springframework.stereotype.Component;

@Component
public class AddressServiceProxyFallback implements AddressServiceProxy {
    @Override
    public AddressModel getAddressById(Long addressID) {
        AddressModel errorAddress = new AddressModel();
        errorAddress.setAddressID(0L);
        errorAddress.setStreet(ExceptionWarnMsg.ADDRESS_API_ERROR.toString());
        errorAddress.setCity(ExceptionWarnMsg.ADDRESS_API_ERROR.toString());
        errorAddress.setCountry(ExceptionWarnMsg.ADDRESS_API_ERROR.toString());
        return errorAddress;
    }
}
