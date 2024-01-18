package fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.service;

import fr.natan.cleanarchitectureaddressservice.domain.entity.Address;
import fr.natan.cleanarchitectureaddressservice.domain.exceptions.AddressNotFoundException;
import fr.natan.cleanarchitectureaddressservice.domain.ports.output.AddressOutputService;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.service.EmployeeService;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.input.feignclient.entity.model.EmployeeModel;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_mapper.AddressMapper;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressDto;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressModel;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressOutputServiceImpl implements AddressOutputService {

    private final AddressRepository addressRepository;
    private final EmployeeService employeeService;

    public AddressOutputServiceImpl(AddressRepository addressRepository, EmployeeService employeeService) {
        this.addressRepository = addressRepository;
        this.employeeService = employeeService;
    }

    @Override
    public List<Address> getAllAddresses() {
        List<AddressModel> addressModels = addressRepository.findByOrderByAddressIDAsc();

        return addressModels.stream()
                .map(AddressMapper::mapModelToClass)
                .collect(Collectors.toList());
    }

    @Override
    public Address createAddress(Address address) {

        AddressModel addressModel = AddressMapper.mapClassToModel(address);
        AddressModel savedAddress = addressRepository.save(addressModel);

        return AddressMapper.mapModelToClass(savedAddress);
    }

    @Override
    public List<Address> getAddressByInfo(AddressDto addressDto) {
        List<AddressModel> addressModels = addressRepository.findByNumAndStreetAndPbAndCityAndCountry(
                addressDto.getNum(), addressDto.getStreet(), addressDto.getPb(), addressDto.getCity(), addressDto.getCountry());
        return addressModels.stream()
                .map(AddressMapper::mapModelToClass)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAddress(Long addressID) {
        addressRepository.deleteById(addressID);
    }

    @Override
    public Optional <Address> getAddressByID(Long addressID) throws AddressNotFoundException {
       AddressModel addressModel = addressRepository.findById(addressID).orElseThrow(
               AddressNotFoundException::new
       );

       return Optional.of(AddressMapper.mapModelToClass(addressModel));
    }

    @Override
    public void updateAddress(Address address) {
        AddressModel addressModel = AddressMapper.mapClassToModel(address);
        AddressModel savedAddress = addressRepository.save(addressModel);
        AddressMapper.mapModelToClass(savedAddress);
    }

    @Override
    public List<EmployeeModel> getEmployeesLivingAtAddress(Long addressID) {
        return employeeService.getEmployeesLivingAtGivenAddress(addressID);
    }
}
