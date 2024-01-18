import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve } from "@angular/router";
import { Observable } from "rxjs";
import { Employee } from "src/app/shared/models/employee/employee.model";
import { EmployeeService } from "../../service-REST/employees.service";

@Injectable({ providedIn: 'root' })
export class GetAllEmployeesResolve implements Resolve<Array<Employee>> {
  constructor(private employeeService: EmployeeService) {}

  resolve(): Observable<Employee[]> {
    return this.employeeService.getAllEmployees();
  }
}

@Injectable({providedIn:'root'})
export class GetEmployeeByIDResolve implements Resolve<Employee> {
  constructor(private employeeService: EmployeeService){}
  resolve(route: ActivatedRouteSnapshot): Observable<Employee> {
    return this.employeeService.getEmployee(route.paramMap.get('employeeID'));
  }
}
@Injectable({providedIn:'root'})
export class GetProjectsAssignedToEmployeeResolve implements Resolve<Array<any>>{
  constructor(private employeeService: EmployeeService){}
  resolve(route: ActivatedRouteSnapshot):Observable<any[]>{
    return this.employeeService.getProjectsAssignedToEmployee(route.paramMap.get('employeeID'))
  }

}
