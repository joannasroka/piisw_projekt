import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from "./landing-page/landing-page.component";
import { LoginComponent } from "./authentication/login/login.component";
import { SignupComponent } from './user/signup/signup.component';
import { ConfirmSignupComponent } from "./user/confirm-signup/confirm-signup.component";
import { TokenGuard } from "./helpers/guards/token.guard";
import { BrowseTicketsComponent } from './tickets/browse-tickets/browse-tickets.component';
import { NotAuthGuard } from "./helpers/guards/not-auth.guard";
import { AuthGuard } from "./helpers/guards/auth.guard";
import { TicketsResolver } from "./tickets/resolvers/tickets-resolver/tickets.resolver";
import { BuyTicketComponent } from './tickets/buy-ticket/buy-ticket.component';
import { BuyTicketResolver } from './tickets/resolvers/buy-ticket-resolver/buy-ticket.resolver';
import { MyTicketsComponent } from './tickets/my-tickets/my-tickets.component';
import { MyTicketsResolver } from './tickets/resolvers/my-tickets-resolver/my-tickets.resolver';
import { PassengerRoleGuard } from "./helpers/guards/passenger-role.guard";
import { InspectTicketsComponent } from './tickets/inspect-tickets/inspect-tickets.component';
import { InspectorRoleGuard } from './helpers/guards/inspector-role.guard';

const routes: Routes = [
  { path: '', component: LandingPageComponent, canActivate: [NotAuthGuard]},
  { path: 'landingPage', component: LandingPageComponent},
  { path: 'login', component: LoginComponent, canActivate: [NotAuthGuard]},
  { path: 'signup', component: SignupComponent, canActivate: [NotAuthGuard]},
  { path: 'confirmSignup', component: ConfirmSignupComponent, canActivate: [TokenGuard, NotAuthGuard]},
  { path: 'browseTickets', component: BrowseTicketsComponent, resolve: {tickets: TicketsResolver}, canActivate: [AuthGuard]},
  { path: 'buyTicket', component: BuyTicketComponent, resolve: {ticketToBuy: BuyTicketResolver}, canActivate: [AuthGuard, PassengerRoleGuard]},
  { path: 'myTickets', component: MyTicketsComponent, resolve: {myTickets: MyTicketsResolver}, canActivate: [AuthGuard, PassengerRoleGuard]},
  { path: 'inspectTickets', component: InspectTicketsComponent, canActivate: [AuthGuard, InspectorRoleGuard]},
  // otherwise redirect to landing page
  { path: '**', redirectTo: 'landingPage'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
