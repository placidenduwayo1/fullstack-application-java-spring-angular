package fr.natan.cleanarchitectureprojectservice.domain.usecase;

import fr.natan.cleanarchitectureprojectservice.domain.entity.Project;
import fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers.*;
import fr.natan.cleanarchitectureprojectservice.domain.ports.input.ProjectInputService;
import fr.natan.cleanarchitectureprojectservice.domain.ports.output.ProjectOutputService;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.CompanyModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.EmployeeModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.model_mapper.ProjectMapper;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models.ProjectDto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProjectInputServiceImpl implements ProjectInputService {
    private final ProjectOutputService projectOutputService;


    public ProjectInputServiceImpl(ProjectOutputService projectOutputService) {
        this.projectOutputService = projectOutputService;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectOutputService.getAllProjects();
    }

    @Override
    public List<Project> getProjectByInfo(ProjectDto projectDto) {
        return projectOutputService.getProjectByInfo(projectDto);
    }

    @Override
    public Optional<Project> getProjectByID(String projectID) throws ProjectNotFoundException {
        Project project = projectOutputService.getProjectByID(projectID).orElseThrow(
                ProjectNotFoundException::new
        );

        return Optional.of(project);
    }

    @Override
    public Optional<EmployeeModel> getEmployeeByID(String employeeID)  {
       return Optional.of(projectOutputService.getEmployeeByID(employeeID));
    }

    @Override
    public Optional<CompanyModel> getCompanyByID(String companyID)  {
        return Optional.of(projectOutputService.getCompanyByID(companyID));
    }

    @Override
    public Project createProject(ProjectDto projectDto) throws ProjectAlreadyExistsException,
            ProjectFieldsEmptyException, ProjectCreationErrorDueToEmployeeAPIException,
            ProjectCreationErrorDueToCompanyAPIException, ProjectCreationErrorDueToNotAcceptedEmployeeStateException {
        ProjectValidation.projectFormatter(projectDto);
        if (!ProjectValidation.areValidProjectFields(projectDto)) {
            throw new ProjectFieldsEmptyException();
        }
        List<Project> projects = getProjectByInfo(projectDto);
        if (projects.size() > 0) {
            throw new ProjectAlreadyExistsException();
        }

        Project project = ProjectMapper.dtoToClass(projectDto);

        Optional<EmployeeModel> employeeModel = getEmployeeByID(projectDto.getEmployeeID());
        Optional<CompanyModel> companyModel = getCompanyByID(projectDto.getCompanyID());

        if(ProjectValidation.isInvalidEmployeeAPI(employeeModel.get())){
            throw new ProjectCreationErrorDueToEmployeeAPIException(employeeModel.toString());
        }
        else if (ProjectValidation.isInvalidCompanyAPI(companyModel.get())){
            throw new ProjectCreationErrorDueToCompanyAPIException(companyModel.toString());
        }

        if(ProjectValidation.isInvalidEmployeeState(employeeModel.get())){
            throw new ProjectCreationErrorDueToNotAcceptedEmployeeStateException();
        }

        project.setProjectID(UUID.randomUUID().toString());
        project.setCreatedDate(LocalDateTime.now(ZoneId.of("Europe/Paris")));
        project.setEmployee(employeeModel.get());
        project.setCompany(companyModel.get());
        return projectOutputService.createProject(project);
    }

    @Override
    public void deleteProject(String projectID) throws ProjectNotFoundException, CompanyIsAssiacetedToProjectException,
            EmployeeIsAssiacetedToProjectException {
        Optional<Project> project = getProjectByID(projectID);

        if(project.isEmpty()){
            throw new ProjectNotFoundException();
        } else if (getProjectsAssignedToCompany(project.get().getCompanyID()).size()>0) {
            throw new CompanyIsAssiacetedToProjectException();
        } else if (getProjectsAssignedToEmployee(project.get().getEmployeeID()).size()>0) {
            throw new EmployeeIsAssiacetedToProjectException();
        }
    }
    @Override
    public Project updateProject(String projectID, ProjectDto projectDto) throws ProjectNotFoundException,
            ProjectCreationErrorDueToEmployeeAPIException, ProjectCreationErrorDueToCompanyAPIException, ProjectFieldsEmptyException,
            ProjectCreationErrorDueToNotAcceptedEmployeeStateException, ProjectAlreadyExistsException {
        ProjectValidation.projectFormatter(projectDto);
        if(!ProjectValidation.areValidProjectFields(projectDto)){
            throw new ProjectFieldsEmptyException();
        }
        Project project = ProjectMapper.dtoToClass(projectDto);
        Optional<Project> createdProject = projectOutputService.getProjectByID(projectID);
        createdProject.ifPresentOrElse(
                value ->{
                    project.setProjectID(value.getProjectID());
                    project.setCompanyID(value.getCompanyID());
                    project.setCreatedDate(value.getCreatedDate());
                },
                ProjectNotFoundException::new
        );
        Optional<EmployeeModel> employeeModel = getEmployeeByID(projectDto.getEmployeeID());
        Optional<CompanyModel> companyModel = getCompanyByID(projectDto.getCompanyID());
        if(ProjectValidation.isInvalidEmployeeAPI(employeeModel.get())){
            throw new ProjectCreationErrorDueToEmployeeAPIException(employeeModel.toString());
        } else if (ProjectValidation.isInvalidCompanyAPI(companyModel.get())) {
            throw new ProjectCreationErrorDueToCompanyAPIException(companyModel.toString());
        }
        if(ProjectValidation.isInvalidEmployeeState(employeeModel.get())){
            throw new ProjectCreationErrorDueToNotAcceptedEmployeeStateException();
        }
        if(getProjectByInfo(projectDto).size()>0){
            throw new ProjectAlreadyExistsException();
        }
        project.setEmployee(employeeModel.get());
        project.setCompany(companyModel.get());

        return projectOutputService.updateProject(project);
    }

    @Override
    public List<Project> getProjectsAssignedToCompany(String companyID) {
        Optional<CompanyModel> company = getCompanyByID(companyID);
        return projectOutputService.getProjectsAssignedToCompany(company.get().getCompanyID());
    }

    @Override
    public List<Project> getProjectsAssignedToEmployee(String emmployeeID) {
        Optional<EmployeeModel> employee = getEmployeeByID(emmployeeID);
        return projectOutputService.getProjectsAssignedToEmployee(employee.get().getEmployeeID());
    }
}
