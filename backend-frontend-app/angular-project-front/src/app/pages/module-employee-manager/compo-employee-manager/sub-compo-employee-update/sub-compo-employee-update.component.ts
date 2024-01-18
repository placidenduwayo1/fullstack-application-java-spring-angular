import { AddressService } from '../../../../shared/services/service-REST/addresses.service';
import { Address } from '../../../../shared/models/address/address.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { EmployeeEvent } from 'src/app/shared/models/events.model';
import { Employee } from 'src/app/shared/models/employee/employee.model';
import { EmployeeState } from 'src/app/shared/models/employee/employee.state';
import { EmployeeType } from 'src/app/shared/models/employee/employee.type';
import { EmployeeService } from 'src/app/shared/services/service-REST/employees.service';

@Component({
  selector: 'app-sub-compo-employee-update',
  templateUrl: './sub-compo-employee-update.component.html',
  styleUrls: ['./sub-compo-employee-update.component.scss'],
})
export class SubCompoEmployeeUpdateComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private employeeService: EmployeeService,
    private fbuilder: FormBuilder,
    private addressService: AddressService
  ) {}

  employeeForm!: FormGroup;
  employeeType = EmployeeType;
  employeeState = EmployeeState;
  addresses!: Array<Address>;
  addressesMap: Map<number, string> = new Map();

  ngOnInit(): void {
    this.addressService.getAllAddresses().subscribe((data: Array<Address>) => {
      this.addresses = data;
      this.addresses.forEach((address: Address) => {
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
      });
    });

    this.activatedRoute
      .data.subscribe((data) => {
        let employee: Employee = data['getEmployeeByIDResolve'];
        console.log(employee);
        this.employeeForm = this.fbuilder.group({
          employeeID: [employee.employeeID],
          firstname: [employee.firstname, Validators.required],
          lastname: [employee.lastname, Validators.required],
          employeeState: [employee.employeeState, Validators.required],
          employeeType: [employee.employeeType, Validators.required],
          addressID: [employee.addressID, Validators.required],
        });
      });
  }

  onSaveEmployeeFormData() {
    if (window.confirm('COMFIRM EMPLOYEE UPDATE')) {
      this.employeeService
        .updateEmployee(this.employeeForm.value)
        .subscribe((employee: Employee) => {
          console.log(employee);
          console.log(EmployeeEvent.UPDATE_EMPLOYEE);
        });
    }
  }
}
