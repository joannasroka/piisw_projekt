import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { TicketResponse } from "../models/response/ticketResponse";
import { TicketPricingType } from "../models/ticketPricingType";

@Component({
  selector: 'app-ticket-row',
  templateUrl: './ticket-row.component.html',
  styleUrls: ['./ticket-row.component.scss']
})
export class TicketRowComponent implements OnInit {
  @Input() ticket!: TicketResponse;
  @Input() ticketPricingType!: TicketPricingType;
  @Input() shouldDisplayBuyButton: boolean = true;
  @Output() buyTicketEvent = new EventEmitter<TicketResponse>();

  TicketPricingType = TicketPricingType;

  constructor() { }

  ngOnInit(): void {
  }

  buyButtonClicked(): void {
    this.buyTicketEvent.emit(this.ticket);
  }

}
