import { SubCompoPrintProjectsForCompanyComponent } from './compo-company-manager/sub-compo-print-projects-for-company/sub-compo-print-projects-for-company.component';
import { GetCompanyByIDResolve, GetProjectsAssignedToCompanyResolve } from './../../shared/services/service-route-resolve/company-resolve/company.route.resolve';
import { SubCompoCompanyUpdateComponent } from './compo-company-manager/sub-compo-company-update/sub-compo-company-update.component';
import { SubCompoCompanyCreateComponent } from './compo-company-manager/sub-compo-company-create/sub-compo-company-create.component';
import { CompoCompanyManagerComponent } from './compo-company-manager/compo-company-manager.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetAllCompaniesResolve } from 'src/app/shared/services/service-route-resolve/company-resolve/company.route.resolve';
import { authGuardService } from 'src/app/core/auth-guard/auth-guard-service.guard';

const routes: Routes = [
  {
    path:'',
    component: CompoCompanyManagerComponent,
    resolve:{
      getAllCompaniesResolve: GetAllCompaniesResolve
    },
    canActivate: [authGuardService],
  },
  {
    path:'company-create',
    component: SubCompoCompanyCreateComponent, canActivate: [authGuardService],
  },
  {
    path:'company-update/:companyID',
    component: SubCompoCompanyUpdateComponent, canActivate: [authGuardService],
    resolve:{
      getCompanyByIDResolve: GetCompanyByIDResolve
    }
  },
  {
    path: 'projects-assignedto-company/:companyID',
    component: SubCompoPrintProjectsForCompanyComponent,canActivate: [authGuardService],
    resolve:{
      getProjectsAssignedToCompanyResolve: GetProjectsAssignedToCompanyResolve
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModuleCompanyManagerRoutingModule { }
