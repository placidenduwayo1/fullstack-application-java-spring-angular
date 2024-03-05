import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '', redirectTo: '/login', pathMatch:'full'
  },
  {
    path: 'accueil',
    loadChildren: () => import('./pages/module-accueil/module-accueil.module')
      .then(module => module.ModuleAccueilModule)
  },
  {
    path: 'addresses-management',
    loadChildren:
      () => import('./pages/module-address-manager/module-address-manager.module')
        .then(m => m.ModuleAddressManagerModule)
  },
  {
    path: 'companies-management',
    loadChildren:
      () => import('./pages/module-company-manager/module-company-manager.module')
        .then(m => m.ModuleCompanyManagerModule)
  },
  {
    path: 'employees-management',
    loadChildren:
      () => import('./pages/module-employee-manager/module-employee-manger.module')
        .then(m => m.ModuleEmployeeManagerModule)
  },
  {
    path: 'projects-management',
    loadChildren:
      () => import('./pages/module-project-manager/module-project-manager.module')
        .then(m => m.ModuleProjectManagerModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }



