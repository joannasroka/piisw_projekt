import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowseTicketsComponent } from './browse-tickets/browse-tickets.component';
import { MatToolbarModule } from "@angular/material/toolbar";
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatButtonToggleModule } from "@angular/material/button-toggle";
import { FormsModule } from "@angular/forms";
import { MatCardModule } from "@angular/material/card";
import { TicketRowComponent } from './ticket-row/ticket-row.component';
import { MatListModule } from "@angular/material/list";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";



@NgModule({
  declarations: [
    BrowseTicketsComponent,
    TicketRowComponent
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
    MatButtonModule
  ]
})
export class TicketsModule { }
