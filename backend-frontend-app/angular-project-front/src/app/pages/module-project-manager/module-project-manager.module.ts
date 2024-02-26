import { GetEmployeeByIDResolve } from 'src/app/shared/services/service-route-resolve/employee-resolve/employee.route.resolve';
import { ReactiveFormsModule } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ModuleProjectManagerRoutingModule } from './module-project-manager-routing.module';
import { CompoProjectManagerComponent } from './compo-project-manager/compo-project-manager.component';
import { SubCompoProjectPrinterComponent } from './compo-project-manager/sub-compo-project-printer/sub-compo-project-printer.component';
import { SubCompoProjectCreateComponent } from './compo-project-manager/sub-compo-project-create/sub-compo-project-create.component';
import { SubcompoProjectUpdateComponent } from './compo-project-manager/subcompo-project-update/subcompo-project-update.component';
import { ButtonModule } from 'primeng/button';


@NgModule({
  declarations: [
    CompoProjectManagerComponent,
    SubCompoProjectPrinterComponent,
    SubCompoProjectCreateComponent,
    SubcompoProjectUpdateComponent
  ],
  imports: [
    CommonModule,
    ModuleProjectManagerRoutingModule,
    TableModule,
    ReactiveFormsModule
  ],
})
export class ModuleProjectManagerModule { }
