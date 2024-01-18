import { CompanyService } from 'src/app/shared/services/service-REST/companies.service';
import { Project } from './../../../../shared/models/project/project.model';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Company } from 'src/app/shared/models/company/company.model';
import { Employee } from 'src/app/shared/models/employee/employee.model';
import { Priority } from 'src/app/shared/models/project/project.priority';
import { ProjectState } from 'src/app/shared/models/project/project.state';
import { EmployeeService } from 'src/app/shared/services/service-REST/employees.service';
import { ProjectService } from 'src/app/shared/services/service-REST/projects.service';


@Component({
  selector: 'app-subcompo-project-update',
  templateUrl: './subcompo-project-update.component.html',
  styleUrls: ['./subcompo-project-update.component.scss']
})
export class SubcompoProjectUpdateComponent implements OnInit {

  projectForm!: FormGroup;

  constructor(private fBuilder: FormBuilder,private employeeService: EmployeeService,
    private companyService: CompanyService, private activatedRoute: ActivatedRoute,
    private projectService: ProjectService) {}

  priorities = Priority;
  states = ProjectState;

  employees!: Array<Employee>;
  employeesMap:Map<string, string> = new Map();
  companies!: Array<Company>
  companiesMap:Map<string, string> = new Map();
  project!: Project;

  ngOnInit(): void {
    this.employeeService
    .getAllEmployees()
    .subscribe((data: Array<Employee>) => {
      this.employees = data;
      this.employees.forEach((employee: Employee) => {
        this.employeesMap.set(
          employee.employeeID,
          employee.firstname +
            ' ' +
            employee.lastname +
            ' email: ' +
            employee.email
        );
      });
    });

    this.companyService.getAllCompanies().subscribe((data: Array<Company>) => {
      this.companies = data;
      this.companies.forEach((company: Company) => {
        this.companiesMap.set(
          company.companyID,
          company.companyName +
            ' ' +
            company.agency
        );
      });
    });

    this.activatedRoute.data.subscribe(data=>{
      let project: Project = data['getProjectByIDResolve'];
      console.log(project);

      this.projectForm = this.fBuilder.group({
        projectID: [project.projectID, Validators.required],
        projectName: [project.projectName, [Validators.required, Validators.minLength(4)]],
        description: [project.description, [Validators.required, Validators.minLength(10)]],
        priority: [project.priority, Validators.required],
        projectState: [project.projectState, Validators.required],
        employeeID: [project.employeeID, Validators.required],
        companyID: [project.companyID, Validators.required],
      });

    });
  }

  onSaveProjectForm(){
    if(window.confirm("CONFIRM PROJECT UPDATE")){
      this.projectService.updateProject(this.projectForm.value).subscribe((newProject: Project)=>{
        console.log(newProject);
      })
    }
  }

}
