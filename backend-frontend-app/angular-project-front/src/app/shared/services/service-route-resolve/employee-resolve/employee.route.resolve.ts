import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, ResolveFn } from "@angular/router";
import { Employee } from "src/app/shared/models/employee/employee.model";
import { EmployeeService } from "../../service-REST/employees.service";

export const GetAllEmployeesResolve: ResolveFn<Array<Employee>> = ()=>{
  return inject(EmployeeService).getAllEmployees();
}

export const GetEmployeeByIDResolve: ResolveFn<Employee> = (route: ActivatedRouteSnapshot)=>{
  return inject(EmployeeService).getEmployee(route.paramMap.get('employeeID'));
}

export const GetProjectsAssignedToEmployeeResolve: ResolveFn<Array<any>> = (route: ActivatedRouteSnapshot)=>{
  return inject(EmployeeService).getProjectsAssignedToEmployee(route.paramMap.get('employeeID'));
}
