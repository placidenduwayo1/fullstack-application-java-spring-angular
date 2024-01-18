import { catchError, Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Employee } from '../../models/employee/employee.model';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  constructor(private httpclient: HttpClient) {}

  handleError() {
    return throwError(() => {
      new Error('Something wrong');
    });
  }

  headers = { 'content-type': 'application/json' };
  dbServer: string = environment.gatewayService+'/clean-archi-bs-ms-employee';

  getAllEmployees(): Observable<Array<Employee>> {
    return this.httpclient
      .get<Array<Employee>>(this.dbServer + '/employees')
      .pipe(catchError(this.handleError));
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.httpclient
      .post<Employee>(this.dbServer + '/employees', employee, {
        headers: this.headers,
      })
      .pipe(catchError(this.handleError));
  }

  deleteEmployee(employeeID: string) {
    return this.httpclient
      .delete(this.dbServer + '/employees/' + employeeID)
      .pipe(catchError(this.handleError));
  }

  getEmployee(employeeID: string|any): Observable<Employee> {
    return this.httpclient
      .get<Employee>(this.dbServer + '/employees/' + employeeID)
      .pipe(catchError(this.handleError));
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    return this.httpclient
      .put<Employee>(
        this.dbServer + '/employees/' + employee.employeeID,
        employee
      )
      .pipe(catchError(this.handleError));
  }

  getProjectsAssignedToEmployee(employeeID:string|any): Observable<Array<any>> {
    return this.httpclient.get<Array<any>> (this.dbServer+'/projects/employees/'+employeeID)
    .pipe(catchError(this.handleError));
  }
}
