package fr.natan.cleanarchitectureprojectservice.domain.usecase;

import fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers.ExceptionWarnMsg;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.CompanyModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.EmployeeModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.enums.EmployeeState;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models.ProjectDto;

public class ProjectValidation {
    public static void projectFormatter(ProjectDto projectDto){
        projectDto.setProjectName(projectDto.getProjectName().strip());
        projectDto.setEmployeeID(projectDto.getEmployeeID().strip());
        projectDto.setCompanyID(projectDto.getCompanyID().strip());

    }

    public static boolean areValidProjectFields(ProjectDto projectDto){
        if(projectDto.getProjectName().isBlank()
        || projectDto.getEmployeeID().isBlank()
        || projectDto.getCompanyID().isBlank()){
            return false;
        }
        return true;
    }

    public static boolean isInvalidEmployeeAPI(EmployeeModel employeeModel){
        if(employeeModel.getEmployeeID().equals(ExceptionWarnMsg.EMPLOYEE_API_ERROR.toString())){
            return true;
        }
        return false;
    }

    public static boolean isInvalidCompanyAPI(CompanyModel companyModel){
        if(companyModel.getCompanyID().equals(ExceptionWarnMsg.COMPANY_API_ERROR.toString())){
            return true;
        }
        return false;
    }

    public static boolean isInvalidEmployeeState(EmployeeModel employeeModel){
        if(employeeModel.getEmployeeState().equals(EmployeeState.HISTORIZED)){
            return true;
        }
        return false;
    }
}
