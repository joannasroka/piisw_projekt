import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { LandingPageComponent } from './landing-page/landing-page.component';
import { MatNativeDateModule, MatRippleModule } from "@angular/material/core";
import { AuthenticationModule } from "./authentication/authentication.module";
import { SharedModule } from "./shared/shared.module";
import { HttpClientModule } from "@angular/common/http";
import { MatMenuModule } from "@angular/material/menu";
import { UserModule } from './user/user.module';
import { MatPasswordStrengthModule } from "@angular-material-extensions/password-strength";
import { TicketsModule } from './tickets/tickets.module';
import { TimeagoModule } from "ngx-timeago";

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent
  ],
    imports: [
        AuthenticationModule,
        SharedModule,
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        LayoutModule,
        MatToolbarModule,
        MatButtonModule,
        MatSidenavModule,
        MatIconModule,
        MatListModule,
        MatRippleModule,
        HttpClientModule,
        MatMenuModule,
        UserModule,
        MatPasswordStrengthModule.forRoot(),
        TicketsModule,
        MatNativeDateModule,
        TimeagoModule.forRoot()
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
