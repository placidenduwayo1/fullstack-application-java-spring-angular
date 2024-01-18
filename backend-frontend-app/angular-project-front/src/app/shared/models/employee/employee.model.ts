import { EmployeeType } from './employee.type';
import { EmployeeState } from './employee.state';

export class Employee {
  employeeID!: string;
  firstname!:string;
  lastname!: string;
  email!: string;
  hireDate!: Date;
  employeeState!: EmployeeState
  employeeType!: EmployeeType
  addressID!: string
}
