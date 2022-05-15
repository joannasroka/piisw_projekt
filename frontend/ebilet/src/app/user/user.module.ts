import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupComponent } from './signup/signup.component';
import { SharedModule } from "../shared/shared.module";
import { MatCardModule } from "@angular/material/card";
import { MatFormFieldModule } from "@angular/material/form-field";
import { ReactiveFormsModule } from "@angular/forms";
import { ExtendedModule, FlexModule } from "@angular/flex-layout";
import { MatButtonModule } from "@angular/material/button";
import { MatInputModule } from "@angular/material/input";
import { NgxTrimDirectiveModule } from "ngx-trim-directive";
import { ConfirmSignupComponent } from './confirm-signup/confirm-signup.component';



@NgModule({
  declarations: [
    SignupComponent,
    ConfirmSignupComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    MatCardModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    FlexModule,
    ExtendedModule,
    MatButtonModule,
    MatInputModule,
    NgxTrimDirectiveModule
  ]
})
export class UserModule { }
