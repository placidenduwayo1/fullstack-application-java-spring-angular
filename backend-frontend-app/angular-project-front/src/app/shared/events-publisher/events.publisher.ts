import { ProjectEvent } from '../models/events.model';
import { EmployeeEvent, AddressEvent, CompanyEvent } from '../models/events.model';
import { Injectable } from "@angular/core";
import { Observable, Subject } from "rxjs";

@Injectable({providedIn:"root"})
export class EmployeeEventPublisher {

  private employeeEventSubject: Subject<EmployeeEvent> = new Subject<EmployeeEvent>(); //create an event publisher
  employeeEnventObservable: Observable<EmployeeEvent> = this.employeeEventSubject.asObservable(); //create an event observable

  publishEmployeeEvent(employeeEvent: EmployeeEvent){
    this.employeeEventSubject.next(employeeEvent);
  }
}

@Injectable({providedIn: "root"})
export class AddressEventPublisher {

  private addressEventSubject: Subject<AddressEvent> = new Subject<AddressEvent>();
  addressEventObservable: Observable<AddressEvent> = this.addressEventSubject.asObservable();
  publishAddressEvent(addressEvent: AddressEvent){
    this.addressEventSubject.next(addressEvent);
  }
}

@Injectable({providedIn:'root'})
export class CompanyEventPublisher {
  private companyEventSubject: Subject<CompanyEvent> = new Subject<CompanyEvent>();
  companyEventObservable: Observable<CompanyEvent> = this.companyEventSubject.asObservable();
  publishCompanyEvent(companyEvent: CompanyEvent){
    this.companyEventSubject.next(companyEvent);
  }
}

@Injectable({providedIn:'root'})
export class ProjectEventPublisher {
  private projectEventSubject : Subject<ProjectEvent> = new Subject<ProjectEvent>();
  projectEventObservable: Observable<ProjectEvent> = this.projectEventSubject.asObservable();
  publishProjectEvent(projectEvent: ProjectEvent){
    this.projectEventSubject.next(projectEvent);
  }
}
