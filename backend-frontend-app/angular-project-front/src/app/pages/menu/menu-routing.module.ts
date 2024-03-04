import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { authGuardService } from 'src/app/core/auth-guard/auth-guard-service.guard';

const routes: Routes = [
  {
    path:'menu', component: MenuComponent, canActivate: [authGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MenuRoutingModule { }
