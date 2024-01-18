package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.services;

import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.fallback.AddressServiceProxyFallback;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.AddressModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clean-archi-bs-ms-address", fallback = AddressServiceProxyFallback.class)
@Qualifier(value = "addressserviceproxy")
public interface AddressServiceProxy {
    @GetMapping(value = "/addresses/{addressID}")
    AddressModel getAddressById(@PathVariable(name = "addressID") Long addressID);
}
