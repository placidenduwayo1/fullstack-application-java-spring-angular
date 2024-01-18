import { Company } from './../../../../shared/models/company/company.model';
import { Employee } from './../../../../shared/models/employee/employee.model';
import { ProjectState } from './../../../../shared/models/project/project.state';
import { Priority } from './../../../../shared/models/project/project.priority';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Project } from 'src/app/shared/models/project/project.model';
import { CompanyService } from 'src/app/shared/services/service-REST/companies.service';
import { EmployeeService } from 'src/app/shared/services/service-REST/employees.service';
import { ProjectService } from 'src/app/shared/services/service-REST/projects.service';

@Component({
  selector: 'app-sub-compo-project-create',
  templateUrl: './sub-compo-project-create.component.html',
  styleUrls: ['./sub-compo-project-create.component.scss'],
})
export class SubCompoProjectCreateComponent implements OnInit {
  projectForm!: FormGroup;

  constructor(
    private fBuilder: FormBuilder,
    private employeeService: EmployeeService,
    private companyService: CompanyService,
    private projectService: ProjectService
  ) {}

  priorities = Priority;
  states = ProjectState;

  employees!: Array<Employee>;
  employeesMap: Map<string, string> = new Map();
  companies!: Array<Company>;
  companiesMap: Map<string, string> = new Map();

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

    this.projectForm = this.fBuilder.group({
      projectID: ['', Validators.required],
      projectName: ['', [Validators.required, Validators.minLength(4)]],
      description: ['', [Validators.required, Validators.minLength(10)]],
      priority: ['', Validators.required],
      projectState: ['', Validators.required],
      employeeID: ['', Validators.required],
      companyID: ['', Validators.required],
    });
  }

  onSaveProjectForm() {
    let project: Project = this.projectForm.value;
    if (window.confirm('CONFIRM PROJECT CREATE')) {
      this.projectService
        .createProject(project)
        .subscribe((newProject: Project) => {
          console.log(newProject);
        });
    }
  }
}
