package fr.natan.cleanarchitectureaddressservice.domain.ports.output;

import fr.natan.cleanarchitectureaddressservice.domain.entity.Address;
import fr.natan.cleanarchitectureaddressservice.domain.exceptions.AddressNotFoundException;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.entity.model.EmployeeModel;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressOutputService {
    List<Address> getAllAddresses();
    Address createAddress(Address address);
    List<Address> getAddressByInfo(AddressDto addressDto);
    void deleteAddress (Long addressID);
    Optional<Address> getAddressByID(Long addressID) throws AddressNotFoundException;
    void updateAddress(Address address);
    List<EmployeeModel> getEmployeesLivingAtAddress(Long addressID);
}
