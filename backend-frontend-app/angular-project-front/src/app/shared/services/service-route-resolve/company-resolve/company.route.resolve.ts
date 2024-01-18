import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve } from "@angular/router";
import { Observable } from "rxjs";
import { Company } from "src/app/shared/models/company/company.model";
import { CompanyService } from "../../service-REST/companies.service";

@Injectable({providedIn:'root'})
export class GetAllCompaniesResolve implements Resolve<Array<Company>> {

  constructor(private companyService: CompanyService){}
  resolve(): Observable<Company[]> {
    return this.companyService.getAllCompanies();
  }
}

@Injectable({providedIn:'root'})
export class GetCompanyByIDResolve implements Resolve<Company> {
  constructor(private companyService: CompanyService){}
  resolve(route: ActivatedRouteSnapshot): Observable<Company>{
   return this.companyService.getCompany(route.paramMap.get('companyID'));
  }
}

@Injectable({providedIn:'root'})
export class GetProjectsAssignedToCompanyResolve implements Resolve<Array<any>> {

  constructor(private companyService: CompanyService){}
  resolve(route: ActivatedRouteSnapshot): Observable<any[]> {
    return this.companyService.getProjectsAssignedToCompany(route.paramMap.get('companyID'));
  }
}
