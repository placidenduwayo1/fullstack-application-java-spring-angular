import { AddressService } from '../../../../shared/services/service-REST/addresses.service';
import { EmployeeEventPublisher } from '../../../../shared/events-publisher/events.publisher';
import { Employee } from '../../../../shared/models/employee/employee.model';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EmployeeEvent } from 'src/app/shared/models/events.model';
import { Address } from 'src/app/shared/models/address/address.model';
import { EmployeeState } from 'src/app/shared/models/employee/employee.state';
import { EmployeeType } from 'src/app/shared/models/employee/employee.type';
import { EmployeeService } from 'src/app/shared/services/service-REST/employees.service';

@Component({
  selector: 'app-sub-compo-employee-create',
  templateUrl: './sub-compo-employee-create.component.html',
  styleUrls: ['./sub-compo-employee-create.component.scss'],
})
export class SubCompoEmployeeCreateComponent implements OnInit {
  employeeForm!: FormGroup;

  constructor(
    private employeeFormBuilder: FormBuilder,
    private employeeEventPublisher: EmployeeEventPublisher,
    private employeeService: EmployeeService,
    private addressService: AddressService,
  ) {}

  employeeState = EmployeeState;
  employeeType = EmployeeType;

  addresses!: Array<Address>;
  addressesMap: Map<number, string> = new Map();

  ngOnInit(): void {
    this.addressService.getAllAddresses().subscribe(data=>{
      this.addresses = data;
      this.addresses.forEach(address=>{
        this.addressesMap.set(
          address.addressID,
          address.num +
            ' ' +
            address.street +
            ', ' +
            address.pb +
            ' ' +
            address.city +
            ', ' +
            address.country
        );
      })
    })
    this.employeeForm = this.employeeFormBuilder.group({
      employeeID: [0, Validators.required],
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      employeeState: [, Validators.required],
      employeeType: [, Validators.required],
      addressID: [, Validators.required],
    });
  }

  onSaveEmployeeFormData() {
    this.employeeEventPublisher.publishEmployeeEvent(
      EmployeeEvent.SAVE_EMPLOYEE_FORM_DATA
    );
    let employee: Employee = this.employeeForm.value;

    if (window.confirm('CONFIRM SAVING NEW EMPLOYEE')) {
      this.employeeService
        .createEmployee(employee)
        .subscribe((newEmployee: Employee) => {
          console.log(newEmployee);
        });
    }
  }
}
