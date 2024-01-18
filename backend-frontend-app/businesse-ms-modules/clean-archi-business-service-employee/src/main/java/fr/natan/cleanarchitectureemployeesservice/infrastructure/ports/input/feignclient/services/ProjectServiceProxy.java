package fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.services;

import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.ProjectModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@FeignClient(name = "clean-archi-bs-ms-project")
@Qualifier(value = "projectserviceproxy")
public interface ProjectServiceProxy {
    @GetMapping(value = "/projects/employees/{employeeID}")
    List<ProjectModel> getProjectsAssignedToEmployee(@PathVariable(name = "employeeID") String employeeID);
}
