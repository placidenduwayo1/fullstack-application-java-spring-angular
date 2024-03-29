import { CompoAccueilComponent } from './compo-accueil/compo-accueil.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuardService } from 'src/app/core/auth-guard/auth-guard-service.guard';

const routes: Routes = [
  {
    path:'', component: CompoAccueilComponent,canActivate: [authGuardService],
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModuleAccueilRoutingModule { }
