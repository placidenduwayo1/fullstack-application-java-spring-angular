package fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.controller;

import fr.natan.cleanarchitectureaddressservice.domain.entity.Address;
import fr.natan.cleanarchitectureaddressservice.domain.exceptions.*;
import fr.natan.cleanarchitectureaddressservice.domain.ports.input.AddressInputService;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.entity.model.EmployeeModel;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class
AddressController {
    private final AddressInputService addressInputService;

    public AddressController(AddressInputService addressInputService) {
        this.addressInputService = addressInputService;
    }

    @GetMapping(value = "/addresses")
    public List<Address> getAllAddresses(){
        return addressInputService.getAllAddresses();
    }

    @PostMapping(value = "/addresses")
    public Address createAddress(@RequestBody AddressDto addressDto) throws AddressFieldsEmptyException, AddressAlreadyExistsException, AddressPBInvalidException, AddressNumInvalidException {
        return addressInputService.createAddress(addressDto);
    }

    @DeleteMapping(value = "/addresses/{addressID}")
    public void deleteAddress(@PathVariable(name = "addressID") Long addressID) throws AddressNotFoundException, AddressAssignedEmployeesException {
        addressInputService.deleteAddress(addressID);
    }

    @PutMapping(value = "/addresses/{addressID}")
    public Address updateAddress(@PathVariable(name = "addressID") Long addressID, @RequestBody AddressDto addressDto)
            throws AddressFieldsEmptyException, AddressNotFoundException, AddressPBInvalidException, AddressNumInvalidException,
            AddressAlreadyExistsException {
        return addressInputService.updateAddress(addressID, addressDto);
    }

    @GetMapping(value = "/addresses/{addressID}")
    public Optional<Address> getAddress(@PathVariable(name = "addressID") Long addressID) throws AddressNotFoundException {
        return addressInputService.getAddressByID(addressID);
    }
    @GetMapping(value = "/employees/addresses/{addressID}")
    public List<EmployeeModel> employeesAtAddressThis(@PathVariable(name = "addressID") Long addressID) throws AddressNotFoundException {
        return addressInputService.getEmployeesLivingAtAddressThis(addressID);
    }
}
