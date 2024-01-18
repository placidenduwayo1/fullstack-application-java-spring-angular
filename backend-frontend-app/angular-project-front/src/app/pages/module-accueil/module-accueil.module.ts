import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ModuleAccueilRoutingModule } from './module-accueil-routing.module';
import { CompoAccueilComponent } from './compo-accueil/compo-accueil.component';


@NgModule({
  declarations: [
    CompoAccueilComponent
  ],
  imports: [
    CommonModule,
    ModuleAccueilRoutingModule
  ]
})
export class ModuleAccueilModule { }
