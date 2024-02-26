import { Router } from '@angular/router';
import { EmployeeEventPublisher } from '../../../../shared/events-publisher/events.publisher';
import { Employee } from '../../../../shared/models/employee/employee.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { EmployeeEvent } from 'src/app/shared/models/events.model';
import { EmployeeService } from 'src/app/shared/services/service-REST/employees.service';

@Component({
  selector: 'app-sub-compo-employee-printer',
  templateUrl: './sub-compo-employee-printer.component.html',
  styleUrls: ['./sub-compo-employee-printer.component.scss']
})
export class SubCompoEmployeePrinterComponent implements OnInit{

  @Input () employeesList!: Array<Employee>;
  @Output () nbEmployeesEventEmitter: EventEmitter<number> = new EventEmitter();

  constructor(private employeeService: EmployeeService,
   private employeeEventPublisher: EmployeeEventPublisher,
   private router: Router){}

   onPrintNumberOfEmployeesEventEmitter(nbEmployees: number) {
    this.nbEmployeesEventEmitter.emit(nbEmployees);
   }

  ngOnInit(): void {
    this.employeeEventPublisher.employeeEnventObservable.subscribe((employeeEvent: EmployeeEvent)=>{

      switch(employeeEvent) {

        case EmployeeEvent.UPDATE_EMPLOYEE_FORM:
          this.router.navigateByUrl('/employee-form-update/'+this.idToUpdate);
          console.log(employeeEvent)
          break;
        case EmployeeEvent.PROJECTS_ASSIGNEDTO_EMPLOYEE:
          this.router.navigateByUrl('/projects-assignedto-employees/'+this.employeeIDProjectsRelated);
          console.log(employeeEvent);
          break;
      }
    });
  }


  idToUpdate!: string;
  onEmployeeUpdate(employee: Employee){
    this.idToUpdate = employee.employeeID;
    this.employeeEventPublisher.publishEmployeeEvent(EmployeeEvent.UPDATE_EMPLOYEE_FORM);
  }

  idToDelete!: string;

  onEmployeeDelete(employeeID: string){
    if(window.confirm("CONFIRM EMPLOYEE DELETE!!")){
      this.employeeService.deleteEmployee(employeeID).subscribe(()=>{
        this.employeeEventPublisher.publishEmployeeEvent(EmployeeEvent.REFRESH);
      });
    }
  }

  employeeIDProjectsRelated!: string;

  onPrintProjectsAssigned(employeeID: string){
    this.employeeIDProjectsRelated=employeeID;
    this.employeeEventPublisher.publishEmployeeEvent(EmployeeEvent.PROJECTS_ASSIGNEDTO_EMPLOYEE);
  }

}
