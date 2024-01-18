package fr.natan.cleanarchitectureaddressservice.domain.ports.input;

import fr.natan.cleanarchitectureaddressservice.domain.entity.Address;
import fr.natan.cleanarchitectureaddressservice.domain.exceptions.*;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.entity.model.EmployeeModel;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressDto;

import java.util.List;
import java.util.Optional;

public interface AddressInputService {
    List<Address> getAllAddresses();
    Address createAddress(AddressDto addressDto) throws AddressFieldsEmptyException, AddressAlreadyExistsException,
            AddressNumInvalidException, AddressPBInvalidException;
    List<Address> getAddressByInfo(AddressDto addressDto);
    void deleteAddress (Long addressID) throws AddressNotFoundException, AddressAssignedEmployeesException;
    Optional<Address> getAddressByID(Long addressID) throws  AddressNotFoundException;
    Address updateAddress(Long addressID, AddressDto addressDto) throws AddressNotFoundException, AddressFieldsEmptyException,
            AddressPBInvalidException, AddressNumInvalidException, AddressAlreadyExistsException;
    List<EmployeeModel> getEmployeesLivingAtAddressThis(Long addressID) throws AddressNotFoundException;
}
