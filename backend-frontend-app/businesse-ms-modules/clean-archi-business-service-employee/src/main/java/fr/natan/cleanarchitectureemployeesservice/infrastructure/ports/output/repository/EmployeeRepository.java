package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.repository;

import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeModel, String> {
    List<EmployeeModel> findByFirstnameAndLastnameAndAddressID(String firstname, String lastname, Long addressID);
    List<EmployeeModel> findByOrderByHireDateDesc();
    List<EmployeeModel> findByAddressID(Long addressID);
}
