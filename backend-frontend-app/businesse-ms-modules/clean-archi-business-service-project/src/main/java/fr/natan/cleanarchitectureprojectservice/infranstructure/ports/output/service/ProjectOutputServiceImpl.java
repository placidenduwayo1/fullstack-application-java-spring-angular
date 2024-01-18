package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.service;

import fr.natan.cleanarchitectureprojectservice.domain.entity.Project;
import fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers.ProjectNotFoundException;
import fr.natan.cleanarchitectureprojectservice.domain.ports.output.ProjectOutputService;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.CompanyModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.EmployeeModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services.CompanyServiceProxy;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services.EmployeeServiceProxy;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.model_mapper.ProjectMapper;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models.ProjectDto;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models.ProjectModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectOutputServiceImpl implements ProjectOutputService {
    private final ProjectRepository projectRepository;
    private final EmployeeServiceProxy employeeServiceProxy;
    private final CompanyServiceProxy companyServiceProxy;

    public ProjectOutputServiceImpl(ProjectRepository projectRepository,
                                    @Qualifier(value = "employeeserviceproxy")EmployeeServiceProxy employeeServiceProxy,
                                    @Qualifier(value = "companyserviceproxy")CompanyServiceProxy companyServiceProxy) {
        this.projectRepository = projectRepository;
        this.employeeServiceProxy = employeeServiceProxy;
        this.companyServiceProxy = companyServiceProxy;
    }

    @Override
    public List<Project> getAllProjects() {
        return utility(projectRepository.findByOrderByCreatedDateDesc());
    }

    @Override
    public List<Project> getProjectByInfo(ProjectDto projectDto) {
        return utility(projectRepository.findByProjectNameAndDescriptionAndProjectStateAndEmployeeIDAndCompanyID(
                projectDto.getProjectName(), projectDto.getDescription(), projectDto.getProjectState(),
                projectDto.getEmployeeID(), projectDto.getCompanyID()));
    }

    @Override
    public Optional<Project> getProjectByID(String projectID) throws ProjectNotFoundException {
        ProjectModel projectModel = projectRepository.findById(projectID).orElseThrow(
                ProjectNotFoundException::new
        );

        return Optional.of(ProjectMapper.mapToClass(projectModel));
    }

    @Override
    public EmployeeModel getEmployeeByID(String employeeID) {
        return employeeServiceProxy.getEmployeeByID(employeeID);
    }

    @Override
    public CompanyModel getCompanyByID(String companyID) {
        return companyServiceProxy.getCompanyByID(companyID);
    }

    @Override
    public Project createProject(Project project) {
        ProjectModel projectModel = ProjectMapper.mapToModel(project);
        projectRepository.save(projectModel);
        return project;
    }

    @Override
    public void deleteProject(Project project) {
        projectRepository.delete(ProjectMapper.mapToModel(project));
    }

    @Override
    public Project updateProject(Project project) {
        ProjectModel projectModel = ProjectMapper.mapToModel(project);
        return ProjectMapper.mapToClass(projectRepository.save(projectModel));
    }

    @Override
    public List<Project> getProjectsAssignedToCompany(String companyID) {
        return utility(projectRepository.findByCompanyID(companyID));
    }

    @Override
    public List<Project> getProjectsAssignedToEmployee(String employeeID) {
        return utility(projectRepository.findByEmployeeID(employeeID));
    }

    private List<Project> utility(List<ProjectModel> models){
        return models.stream()
                .map(ProjectMapper::mapToClass)
                .toList();
    }
}
