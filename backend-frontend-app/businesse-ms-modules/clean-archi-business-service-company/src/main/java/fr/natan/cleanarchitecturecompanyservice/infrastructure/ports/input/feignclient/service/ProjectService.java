package fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.feignclient.service;

import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.feignclient.model.ProjectModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "clean-archi-bs-ms-project")
public interface ProjectService {
    @GetMapping(value = "/projects/companies/{companyID}")
    List<ProjectModel> getProjectsAssignedCompany(@PathVariable(name = "companyID") String companyID);
}
