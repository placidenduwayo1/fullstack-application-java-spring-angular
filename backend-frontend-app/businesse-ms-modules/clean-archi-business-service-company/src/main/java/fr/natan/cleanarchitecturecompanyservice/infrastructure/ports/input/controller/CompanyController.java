package fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.controller;

import fr.natan.cleanarchitecturecompanyservice.domain.entity.Company;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyAlreadyExistsException;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyAssociedProjectsException;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyFieldsEmptyException;
import fr.natan.cleanarchitecturecompanyservice.domain.exceptions.CompanyNotFoundException;
import fr.natan.cleanarchitecturecompanyservice.domain.ports.input.CompanyInputSevice;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.input.feignclient.model.ProjectModel;
import fr.natan.cleanarchitecturecompanyservice.infrastructure.ports.output.models.CompanyDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
public class CompanyController {
    private final CompanyInputSevice companyInputSevice;

    public CompanyController(CompanyInputSevice companyInputSevice) {
        this.companyInputSevice = companyInputSevice;
    }

    @GetMapping(value = "/companies")
    public List<Company> getAllCompanies()  {
        return companyInputSevice.getAllCompanies();
    }

    @PostMapping(value = "/companies")
    public Company createCompany(@RequestBody CompanyDto companyDto) throws CompanyAlreadyExistsException, CompanyFieldsEmptyException {
        return companyInputSevice.createCompany(companyDto);
    }

    @PutMapping(value = "/companies/{companyID}")
    public Company updateCompany(@PathVariable(name = "companyID") String companyID, @RequestBody CompanyDto companyDto)
            throws CompanyNotFoundException, CompanyFieldsEmptyException, CompanyAlreadyExistsException {
        return companyInputSevice.updateCompany(companyID, companyDto);
    }

    @GetMapping(value = "/companies/{companyID}")
    public Optional<Company> getCompanyByID(@PathVariable(name = "companyID") String companyID) throws CompanyNotFoundException {
        return companyInputSevice.getCompanyByID(companyID);
    }

    @DeleteMapping(value = "/companies/{companyID}")
    public void deleteCompany(@PathVariable(name = "companyID") String companyID) throws CompanyNotFoundException, CompanyAssociedProjectsException {
        companyInputSevice.deleteCompany(companyID);
    }

    @GetMapping(value = "/projects/companies/{companyID}")
    public List<ProjectModel> getProjectsAssignedToCompany(@PathVariable(name = "companyID") String companyID) throws CompanyNotFoundException {
        return companyInputSevice.getProjectsAssignedToCompany(companyID);
    }
}
