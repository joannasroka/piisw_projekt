import { NgModule } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { BrowseTicketsComponent } from './browse-tickets/browse-tickets.component';
import { MatToolbarModule } from "@angular/material/toolbar";
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatButtonToggleModule } from "@angular/material/button-toggle";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { TicketRowComponent } from './ticket-row/ticket-row.component';
import { MatListModule } from "@angular/material/list";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { BuyTicketComponent } from './buy-ticket/buy-ticket.component';
import { MatStepperModule } from "@angular/material/stepper";
import { SharedModule } from "../shared/shared.module";
import { RouterModule } from "@angular/router";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MyTicketsComponent } from './my-tickets/my-tickets.component';


@NgModule({
  declarations: [
    BrowseTicketsComponent,
    TicketRowComponent,
    BuyTicketComponent,
    MyTicketsComponent
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    FlexLayoutModule,
    MatButtonToggleModule,
    FormsModule,
    MatCardModule,
    MatListModule,
    MatIconModule,
    MatButtonModule,
    MatStepperModule,
    SharedModule,
    RouterModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule
  ],
  providers: [CurrencyPipe]
})
export class TicketsModule { }
