import { SubCompoPrintEmployeesAtAddressComponent } from './compo-address-manager/sub-compo-print-employees-at-address/sub-compo-print-employees-at-address.component';
import { GetAddressByIDResolve, GetEmployeesAtAddressResolve } from './../../shared/services/service-route-resolve/address-resolve/addresse.route.resolve';
import { CompoAddressPrinterComponent as CompoAddressManagerComponent } from './compo-address-manager/compo-address-manager.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubCompoAddressCreateComponent } from './compo-address-manager/sub-compo-address-create/sub-compo-address-create.component';
import { SubCompoAddressUpdateComponent } from './compo-address-manager/sub-compo-address-update/sub-compo-address-update.component';
import { GetAllAddressesResolve } from 'src/app/shared/services/service-route-resolve/address-resolve/addresse.route.resolve';
import { authGuardService } from 'src/app/core/auth-guard/auth-guard-service.guard';

const routes: Routes = [
  {
    path :"", component: CompoAddressManagerComponent,
    resolve:{
      getAllAddressesResolve: GetAllAddressesResolve
    },
    canActivate: [authGuardService]
  }
  ,
  {
    path:"address-form-create",
    component: SubCompoAddressCreateComponent,
    canActivate: [authGuardService]
  },
  {
    path:'address-form-update/:addressID',
    component: SubCompoAddressUpdateComponent,
    resolve:{
      getAddressByIDResolve: GetAddressByIDResolve
    },
    canActivate: [authGuardService]
  },
  {
    path:'employees-at-address/:addressID',
    component: SubCompoPrintEmployeesAtAddressComponent,
    resolve:{
      getEmployeesAtAddressResolve: GetEmployeesAtAddressResolve
    },
    canActivate: [authGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModuleAddressManagerRoutingModule { }

