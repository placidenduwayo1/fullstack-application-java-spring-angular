import { Company } from '../../../../shared/models/company/company.model';
import { Component } from '@angular/core';
import { CompanyType } from 'src/app/shared/models/company/company.type';
import { CompanyService } from 'src/app/shared/services/service-REST/companies.service';

@Component({
  selector: 'app-sub-compo-company-create',
  templateUrl: './sub-compo-company-create.component.html',
  styleUrls: ['./sub-compo-company-create.component.scss']
})
export class SubCompoCompanyCreateComponent  {

  constructor(private companyService: CompanyService) { }
  companyTypes = CompanyType;

  company: Company = new Company();
  onCompanyCreate(){
    if(window.confirm("COMFIRM COMPANY CREATE")){
      this.companyService.createCompany(this.company).subscribe((newCompany: Company)=>{
        console.log(newCompany)
      })
    }
  }
}
