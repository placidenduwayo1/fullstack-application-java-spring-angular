import { SubcompoProjectUpdateComponent } from './compo-project-manager/subcompo-project-update/subcompo-project-update.component';
import { SubCompoProjectCreateComponent } from './compo-project-manager/sub-compo-project-create/sub-compo-project-create.component';
import { CompoProjectManagerComponent } from './compo-project-manager/compo-project-manager.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GetAllProjectsResolve, GetProjectByIDResolve } from 'src/app/shared/services/service-route-resolve/project-resolve/route.resolve';
import { authGuardService } from 'src/app/core/auth-guard/auth-guard-service.guard';

const routes: Routes = [
  {
    path:'',
    component: CompoProjectManagerComponent,canActivate: [authGuardService],
    resolve:{
      getAllProjectsResolve: GetAllProjectsResolve
    }
  },
  {
    path:'project-create',
    component: SubCompoProjectCreateComponent,
    canActivate: [authGuardService],
  },
  {
    path:'project-update/:projectID',
    component: SubcompoProjectUpdateComponent,
    canActivate: [authGuardService],
    resolve:{
      getProjectByIDResolve:GetProjectByIDResolve
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModuleProjectManagerRoutingModule { }
