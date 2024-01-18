package fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_mapper;

import fr.natan.cleanarchitectureaddressservice.domain.entity.Address;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressDto;
import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressModel;
import org.springframework.beans.BeanUtils;

public class AddressMapper {

    public static AddressModel mapClassToModel(Address address){
        AddressModel addressModel = new AddressModel();
        BeanUtils.copyProperties(address, addressModel);

        return addressModel;
    }

    public static Address mapModelToClass(AddressModel addressModel){
        Address address = new Address();
        BeanUtils.copyProperties(addressModel, address);

        return address;
    }

    public static Address mapDtoToClass(AddressDto addressDto) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDto, address);
        return  address;
    }
}
