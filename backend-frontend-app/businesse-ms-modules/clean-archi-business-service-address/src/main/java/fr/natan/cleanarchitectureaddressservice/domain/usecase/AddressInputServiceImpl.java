package fr.natan.cleanarchitectureaddressservice.domain.usecase;

import fr.natan.cleanarchitectureaddressservice.domain.entity.Address;
import fr.natan.cleanarchitectureaddressservice.domain.exceptions.*;
import fr.natan.cleanarchitectureaddressservice.domain.ports.input.AddressInputService;
import fr.natan.cleanarchitectureaddressservice.domain.ports.output.AddressOutputService;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.entity.model.EmployeeModel;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_mapper.AddressMapper;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressDto;

import java.util.List;
import java.util.Optional;

public class AddressInputServiceImpl implements AddressInputService {

    private final AddressOutputService addressOutputService;
    public AddressInputServiceImpl(AddressOutputService addressOutputService) {
        this.addressOutputService = addressOutputService;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressOutputService.getAllAddresses();
    }

    @Override
    public Address createAddress(AddressDto addressDto) throws AddressFieldsEmptyException, AddressAlreadyExistsException,
            AddressNumInvalidException, AddressPBInvalidException {

        AddressValidation.addressFormatter(addressDto);

        if (!AddressValidation.areValidAddressTextFields(addressDto)) {
            throw new AddressFieldsEmptyException();
        } else if (!AddressValidation.isValidNum(addressDto.getNum())) {
            throw new AddressNumInvalidException();
        } else if (!AddressValidation.isValidPb(addressDto.getPb())) {
            throw new AddressPBInvalidException();
        }
        if (getAddressByInfo(addressDto).size() > 0) {
            throw new AddressAlreadyExistsException();
        }

        Address address = AddressMapper.mapDtoToClass(addressDto);

        return addressOutputService.createAddress(address);
    }

    @Override
    public List<Address> getAddressByInfo(AddressDto addressDto) {
        return addressOutputService.getAddressByInfo(addressDto);
    }

    @Override
    public void deleteAddress(Long addressID) throws AddressNotFoundException, AddressAssignedEmployeesException {
        Optional<Address> address = getAddressByID(addressID);
        List<EmployeeModel> employees = getEmployeesLivingAtAddressThis(address.get().getAddressID());
        if(employees.size()>0){
            throw new AddressAssignedEmployeesException();
        }
        addressOutputService.deleteAddress(addressID);
    }

    @Override
    public Optional<Address> getAddressByID(Long addressID) throws AddressNotFoundException {
        return Optional.of(addressOutputService.getAddressByID(addressID).orElseThrow(
                AddressNotFoundException::new
        ));
    }


    @Override
    public Address updateAddress(Long addressID, AddressDto addressDto) throws AddressNotFoundException,
            AddressFieldsEmptyException, AddressPBInvalidException, AddressNumInvalidException, AddressAlreadyExistsException {
        AddressValidation.addressFormatter(addressDto);
        if(!AddressValidation.isValidPb(addressDto.getPb())){
            throw new AddressPBInvalidException();
        }
        else if(!AddressValidation.isValidNum(addressDto.getNum())){
            throw new AddressNumInvalidException();
        } else if (!AddressValidation.areValidAddressTextFields(addressDto)) {
            throw new AddressFieldsEmptyException();
        }
        if(getAddressByInfo(addressDto).size()>0){
            throw new AddressAlreadyExistsException();
        }
        Address address = AddressMapper.mapDtoToClass(addressDto);
        Optional<Address> createdAddress = getAddressByID(addressID);
        createdAddress.ifPresentOrElse(value -> {
                    address.setAddressID(value.getAddressID());
                   addressOutputService.updateAddress(address);
                },
                AddressNotFoundException::new);

        return address;
    }

    @Override
    public List<EmployeeModel> getEmployeesLivingAtAddressThis(Long addressID) throws AddressNotFoundException {
        Optional<Address> address = getAddressByID(addressID);
        return addressOutputService.getEmployeesLivingAtAddress(address.get().getAddressID());
    }
}
