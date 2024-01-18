package fr.natan.cleanarchitectureaddressservice.domain.usecase;

import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressDto;

@SuppressWarnings("ALL")
public class AddressValidation {

    public static boolean areValidAddressTextFields(AddressDto addressDto){
        if(addressDto.getStreet().isBlank()
                || addressDto.getCity().isBlank()
                || addressDto.getCountry().isBlank()){
            return false;
        }
        return true;
    }

    public static boolean isValidNum(int num){
        if(num<=0){
            return false;
        }
        return true;
    }

    public static boolean isValidPb(int pb){
        if(pb<=10000){
            return false;
        }
        return true;
    }

    public static void addressFormatter(AddressDto addressDto){
        addressDto.setStreet(addressDto.getStreet().strip());
        addressDto.setCity(addressDto.getCity().strip());
        addressDto.setCountry(addressDto.getCountry().strip());
    }
}
