import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatLegacyButtonModule as MatButtonModule } from '@angular/material/legacy-button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatLegacyListModule as MatListModule } from '@angular/material/legacy-list';
import { httpInterceptorProvider } from './core/interceptor';
import { LoginModule } from './core/login/login.module';
import { MyMenuModule } from './pages/menu/menu.module';
import { AppSessionComponent } from './pages/app-session/app-session.component';


@NgModule({
  declarations: [AppComponent, AppSessionComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    LayoutModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    BrowserAnimationsModule,
    LoginModule, MyMenuModule
  ],
  providers: [httpInterceptorProvider],
  bootstrap: [AppComponent],
})
export class AppModule {}
