import { Component, Input, OnInit } from '@angular/core';
import { TicketResponse } from "../models/response/ticketResponse";
import { TicketPricingType } from "../models/ticketPricingType";

@Component({
  selector: 'app-ticket-row',
  templateUrl: './ticket-row.component.html',
  styleUrls: ['./ticket-row.component.scss']
})
export class TicketRowComponent implements OnInit {
  @Input() ticket!: TicketResponse;
  @Input() ticketPricingType!: TicketPricingType ;

  TicketPricingType = TicketPricingType;

  constructor() { }

  ngOnInit(): void {
  }

}
