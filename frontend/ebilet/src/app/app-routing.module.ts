import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from "./landing-page/landing-page.component";

const routes: Routes = [
  { path: '', component: LandingPageComponent},
  { path: 'landingPage', component: LandingPageComponent},
  // otherwise redirect to landing page
  { path: '**', redirectTo: 'landingPage'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
