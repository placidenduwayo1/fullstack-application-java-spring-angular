package fr.natan.cleanarchitectureemployeesservice.domain.exception_metrier;

public class EmployeeCreationErrorDueToAddressAPIException extends Exception{
    public EmployeeCreationErrorDueToAddressAPIException(String message) {
        super(message);
    }
}
