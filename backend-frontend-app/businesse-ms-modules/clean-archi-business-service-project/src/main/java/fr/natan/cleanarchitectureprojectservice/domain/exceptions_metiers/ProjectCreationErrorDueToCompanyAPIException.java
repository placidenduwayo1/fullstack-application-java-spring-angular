package fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers;

public class ProjectCreationErrorDueToCompanyAPIException extends Throwable {
    public ProjectCreationErrorDueToCompanyAPIException(String message) {
        super(message);
    }
}
