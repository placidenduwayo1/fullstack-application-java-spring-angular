import { ModuleProjectManagerModule } from './pages/module-project-manager/module-project-manager.module';
import { ModuleCompanyManagerModule } from './pages/module-company-manager/module-company-manager.module';
import { ModuleEmployeeManagerModule } from './pages/module-employee-manager/module-employee-manger.module';
import { ModuleAddressManagerModule } from './pages/module-address-manager/module-address-manager.module';
import { ModuleAccueilModule } from './pages/module-accueil/module-accueil.module';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { BaseMenuComponent } from './pages/base-menu/base-menu.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatLegacyButtonModule as MatButtonModule } from '@angular/material/legacy-button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatLegacyListModule as MatListModule } from '@angular/material/legacy-list';

@NgModule({
  declarations: [AppComponent, BaseMenuComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    BrowserAnimationsModule,
    ModuleAccueilModule,
    ModuleAddressManagerModule,
    ModuleEmployeeManagerModule,
    ModuleCompanyManagerModule,
    ModuleProjectManagerModule,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
