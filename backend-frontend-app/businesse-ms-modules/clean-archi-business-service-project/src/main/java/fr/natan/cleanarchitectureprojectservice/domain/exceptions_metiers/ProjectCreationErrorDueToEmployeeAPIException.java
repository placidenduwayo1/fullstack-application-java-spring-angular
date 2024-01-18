package fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers;

public class ProjectCreationErrorDueToEmployeeAPIException extends Exception {
    public ProjectCreationErrorDueToEmployeeAPIException(String message) {
        super(message);
    }
}
