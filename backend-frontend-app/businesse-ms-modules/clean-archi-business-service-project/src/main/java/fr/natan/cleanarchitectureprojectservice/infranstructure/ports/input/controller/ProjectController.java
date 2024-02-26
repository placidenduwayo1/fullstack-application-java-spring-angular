package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.controller;

import fr.natan.cleanarchitectureprojectservice.domain.entity.Project;
import fr.natan.cleanarchitectureprojectservice.domain.exceptions_metiers.*;
import fr.natan.cleanarchitectureprojectservice.domain.ports.input.ProjectInputService;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.EmployeeModel;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services.CompanyServiceProxy;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.services.EmployeeServiceProxy;
import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.output.models.ProjectDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(value = "*")
@RestController
public class ProjectController {
    private final ProjectInputService projectInputService;
    private final EmployeeServiceProxy employeeServiceProxy;
    private final CompanyServiceProxy companyServiceProxy;

    public ProjectController(ProjectInputService projectInputService,
                             @Qualifier(value = "employeeserviceproxy") EmployeeServiceProxy employeeServiceProxy,
                             @Qualifier(value = "companyserviceproxy") CompanyServiceProxy companyServiceProxy) {
        this.projectInputService = projectInputService;
        this.employeeServiceProxy = employeeServiceProxy;
        this.companyServiceProxy = companyServiceProxy;
    }

    @GetMapping(value = "/projects")
    public List<Project> getAllProjects() {
        List<Project> projects = projectInputService.getAllProjects();
        projects.forEach(project -> {
            project.setCompany(companyServiceProxy.getCompanyByID(project.getCompanyID()));
            project.setEmployee(employeeServiceProxy.getEmployeeByID(project.getEmployeeID()));
        });
        return projects;
    }

    @PostMapping(value = "/projects")
    public Project createProject(@RequestBody ProjectDto projectDto) throws ProjectAlreadyExistsException,
            ProjectFieldsEmptyException, ProjectCreationErrorDueToEmployeeAPIException, ProjectCreationErrorDueToCompanyAPIException, ProjectCreationErrorDueToNotAcceptedEmployeeStateException {
        Project createdProject = projectInputService.createProject(projectDto);
        createdProject.setEmployee(employeeServiceProxy.getEmployeeByID(createdProject.getEmployeeID()));
        createdProject.setCompany(companyServiceProxy.getCompanyByID(createdProject.getCompanyID()));
        return createdProject;
    }

    @PutMapping(value = "/projects/{projectID}")
    public Project updateProject(@PathVariable(name = "projectID") String projectID, @RequestBody ProjectDto projectDto) throws
            ProjectNotFoundException, ProjectCreationErrorDueToEmployeeAPIException, ProjectCreationErrorDueToCompanyAPIException,
            ProjectFieldsEmptyException, ProjectCreationErrorDueToNotAcceptedEmployeeStateException, ProjectAlreadyExistsException {
        Project updatedProject = projectInputService.updateProject(projectID, projectDto);
        updatedProject.setEmployee(employeeServiceProxy.getEmployeeByID(updatedProject.getEmployeeID()));
        updatedProject.setCompany(companyServiceProxy.getCompanyByID(updatedProject.getCompanyID()));
        return updatedProject;
    }
    @DeleteMapping(value = "/projects/{projectID}")
    public void deleteProject(@PathVariable(name = "projectID") String projectID) throws ProjectNotFoundException,
            EmployeeIsAssiacetedToProjectException, CompanyIsAssiacetedToProjectException {
        projectInputService.deleteProject(projectID);
    }

    @GetMapping(value = "/employees/{employeeID}")
    public EmployeeModel getEmployee(@PathVariable(name = "employeeID") String employeeID) {
        return employeeServiceProxy.getEmployeeByID(employeeID);
    }
    @GetMapping(value = "/projects/{projectID}")
    public Project getProject(@PathVariable(name = "projectID") String projectID) throws ProjectNotFoundException {
        Project project = projectInputService.getProjectByID(projectID).orElseThrow(ProjectNotFoundException::new);
        project.setCompany(companyServiceProxy.getCompanyByID(project.getCompanyID()));
        project.setEmployee(employeeServiceProxy.getEmployeeByID(project.getEmployeeID()));

        return project;
    }
    @GetMapping(value = "/projects/companies/{companyID}")
    public List<Project> getProjectAssignedToCompany(@PathVariable(name = "companyID") String companyID) {
        List<Project> projects = projectInputService.getProjectsAssignedToCompany(companyID);
        projects.forEach(project -> {
            project.setCompany(companyServiceProxy.getCompanyByID(project.getCompanyID()));
            project.setEmployee(employeeServiceProxy.getEmployeeByID(project.getEmployeeID()));
        });

        return projects;
    }
    @GetMapping(value = "/projects/employees/{employeeID}")
    public List<Project> getProjectAssignedToEmployee(@PathVariable(name = "employeeID") String employeeID) {
        List<Project> projects = projectInputService.getProjectsAssignedToEmployee(employeeID);
        projects.forEach(project -> {
            project.setEmployee(employeeServiceProxy.getEmployeeByID(project.getEmployeeID()));
            project.setCompany(companyServiceProxy.getCompanyByID(project.getCompanyID()));
        });
        return projects;
    }
}
