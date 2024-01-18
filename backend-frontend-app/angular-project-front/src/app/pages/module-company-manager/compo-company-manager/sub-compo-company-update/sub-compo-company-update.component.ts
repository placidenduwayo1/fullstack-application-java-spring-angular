import { Company } from './../../../../shared/models/company/company.model';
import { CompanyEventPublisher } from '../../../../shared/events-publisher/events.publisher';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { CompanyEvent } from 'src/app/shared/models/events.model';
import { CompanyType } from 'src/app/shared/models/company/company.type';
import { CompanyService } from 'src/app/shared/services/service-REST/companies.service';

@Component({
  selector: 'app-sub-compo-company-update',
  templateUrl: './sub-compo-company-update.component.html',
  styleUrls: ['./sub-compo-company-update.component.scss'],
})
export class SubCompoCompanyUpdateComponent implements OnInit {
  companyForm!: FormGroup;
  constructor(
    private activatedRoute: ActivatedRoute,
    private fbuilder: FormBuilder,
    private companyService: CompanyService,
    private companyEventPublisher: CompanyEventPublisher
  ) {}

  companyTypes = CompanyType;

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data=>{
      let company: Company = data['getCompanyByIDResolve'];
      console.log(company);
      this.companyForm = this.fbuilder.group({
        companyID: [company.companyID],
        companyName: [
          company.companyName,
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(20),
          ],
        ],
        companyType: [company.companyType, Validators.required],
        agency: [company.agency,[Validators.required, Validators.minLength(5)]]
      });
    });
  }

  onCompanyUpdate() {
    let company:Company = this.companyForm.value;
    if(window.confirm("COMPANY CONFIRM UPDATE")){
      this.companyService.updateCompany(company).subscribe((updatedCompany: Company)=>{
        this.companyEventPublisher.publishCompanyEvent(CompanyEvent.UPDATE_COMPANY);
        console.log(updatedCompany);
      })
    }
  }
}
