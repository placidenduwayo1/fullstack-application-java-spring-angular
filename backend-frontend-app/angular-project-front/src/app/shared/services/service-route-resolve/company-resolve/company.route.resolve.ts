import { inject } from "@angular/core";
import { ActivatedRouteSnapshot, ResolveFn } from "@angular/router";
import { Company } from "src/app/shared/models/company/company.model";
import { CompanyService } from "../../service-REST/companies.service";

export const GetAllCompaniesResolve: ResolveFn<Array<Company>> = ()=>{
  return inject(CompanyService).getAllCompanies();
}
export const GetCompanyByIDResolve: ResolveFn<Company> = (route: ActivatedRouteSnapshot)=>{
  return inject(CompanyService).getCompany(route.paramMap.get('companyID'))
}

export const GetProjectsAssignedToCompanyResolve: ResolveFn<Array<any>> = (route: ActivatedRouteSnapshot)=>{
  return inject(CompanyService).getProjectsAssignedToCompany(route.paramMap.get('companyID'));
}