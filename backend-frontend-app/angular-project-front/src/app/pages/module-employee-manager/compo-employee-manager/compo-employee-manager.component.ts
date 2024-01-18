import { EmployeeEventPublisher } from '../../../shared/events-publisher/events.publisher';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { EmployeeEvent } from 'src/app/shared/models/events.model';
import { Employee } from 'src/app/shared/models/employee/employee.model';
import { EmployeeService } from 'src/app/shared/services/service-REST/employees.service';

@Component({
  selector: 'app-compo-employee-manager',
  templateUrl: './compo-employee-manager.component.html',
  styleUrls: ['./compo-employee-manager.component.scss'],
})
export class CompoEmployeeManagerComponent implements OnInit {
  constructor(
    private activatedRoute: ActivatedRoute,
    private employeeEventPubliser: EmployeeEventPublisher,
    private employeeService: EmployeeService,
    private router : Router
  ) {}

  employeesList!: Array<Employee>;

  onPrintEmployees() {
    this.employeeEventPubliser.publishEmployeeEvent(
      EmployeeEvent.GET_ALL_EMPLOYEES
    );
  }

  ngOnInit(): void {

    this.employeeEventPubliser.employeeEnventObservable.subscribe(
      (employeeEvent: EmployeeEvent) => {

        switch (employeeEvent) {
          case EmployeeEvent.GET_ALL_EMPLOYEES:
            this.activatedRoute.data.subscribe((employees) => {
              this.employeesList = employees['getAllEmployeesResolve'];
            });
            console.log(employeeEvent);
            break;

          case EmployeeEvent.CREATE_EMPLOYEE_FORM:
            console.log(employeeEvent);
            this.router.navigateByUrl("/employee-form-create")
            break;

          case EmployeeEvent.REFRESH:
            this.employeeService.getAllEmployees().subscribe((employees: Array<Employee>)=>{
              this.employeesList = employees;
            })
            console.log(employeeEvent);
            break;
        }
      }
    );
  }

  onCreateEmployee(){
    this.employeeEventPubliser.publishEmployeeEvent(EmployeeEvent.CREATE_EMPLOYEE_FORM);
  }
}
