import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BrowseTicketsComponent } from './browse-tickets/browse-tickets.component';
import { MatToolbarModule } from "@angular/material/toolbar";
import { FlexLayoutModule } from "@angular/flex-layout";
import { MatButtonToggleModule } from "@angular/material/button-toggle";



@NgModule({
  declarations: [
    BrowseTicketsComponent
  ],
  imports: [
    CommonModule,
    MatToolbarModule,
    FlexLayoutModule,
    MatButtonToggleModule
  ]
})
export class TicketsModule { }
