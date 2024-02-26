import { Router } from '@angular/router';
import { CompanyEventPublisher } from '../../../../shared/events-publisher/events.publisher';
import { Company } from '../../../../shared/models/company/company.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { CompanyEvent } from 'src/app/shared/models/events.model';
import { CompanyService } from 'src/app/shared/services/service-REST/companies.service';

@Component({
  selector: 'app-sub-compo-company-printer',
  templateUrl: './sub-compo-company-printer.component.html',
  styleUrls: ['./sub-compo-company-printer.component.scss']
})
export class SubCompoCompanyPrinterComponent implements OnInit {

  constructor(private companyEventPublisher: CompanyEventPublisher,
    private companyService: CompanyService,
    private router : Router) { }

  @Input () companies!: Array<Company>;
  @Output () nbrOfCompaniesEventEmitter : EventEmitter<number> = new EventEmitter();

  ngOnInit(): void {
    this.companyEventPublisher.companyEventObservable.subscribe((companyEvent: CompanyEvent)=>{
      switch(companyEvent) {
        case CompanyEvent.UPDATE_COMPANY_FORM:
          this.router.navigateByUrl('company-update/'+this.idToUpdate);
          console.log(companyEvent);
          break;
        case CompanyEvent.PROJECTS_ASSIGNEDTO_COMPANY:
          this.router.navigateByUrl('projects-assignedto-company/'+this.companyIDProjectsRelated);
          console.log(companyEvent);
          break;
      }
    })
  }

  printNumberOfCompaniesEventEmitter(nb: number){
    this.nbrOfCompaniesEventEmitter.emit(nb);
  }

  idToUpdate!: string;

  onCompanyUpdate(company: Company){
    this.idToUpdate = company.companyID;
    this.companyEventPublisher.publishCompanyEvent(CompanyEvent.UPDATE_COMPANY_FORM)
  }

  onCompanyDelete(companyID: string){
    if(window.confirm('CONFIRM COMPANY DELETE')){
      this.companyService.deleteCompany(companyID).subscribe(
        ()=>{
          this.companyEventPublisher.publishCompanyEvent(CompanyEvent.REFRESH);
        }
      );
    }
  }

  companyIDProjectsRelated!: string;
  onPrintProjectsRelated(companyID: string){
    this.companyIDProjectsRelated = companyID;
    this.companyEventPublisher.publishCompanyEvent(CompanyEvent.PROJECTS_ASSIGNEDTO_COMPANY)
  }
}
