package fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.repository;

import fr.natan.cleanarchitectureaddressservice.infrastructure.ports.output.address_models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<AddressModel, Long> {
    List<AddressModel> findByOrderByAddressIDAsc();
    List<AddressModel> findByNumAndStreetAndPbAndCityAndCountry(int num, String street, int pb, String city, String country);
}
