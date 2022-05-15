import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from "./landing-page/landing-page.component";
import { LoginComponent } from "./authentication/login/login.component";
import { SignupComponent } from './user/signup/signup.component';
import { ConfirmSignupComponent } from "./user/confirm-signup/confirm-signup.component";
import { TokenGuard } from "./helpers/guards/token.guard";

const routes: Routes = [
  { path: '', component: LandingPageComponent},
  { path: 'landingPage', component: LandingPageComponent},
  { path: 'login', component: LoginComponent},
  { path: 'signup', component: SignupComponent},
  { path: 'confirmSignup', component: ConfirmSignupComponent, canActivate: [TokenGuard]},
  // otherwise redirect to landing page
  { path: '**', redirectTo: 'landingPage'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
