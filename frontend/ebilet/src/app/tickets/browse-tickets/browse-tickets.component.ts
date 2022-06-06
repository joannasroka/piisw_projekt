import { Component, OnInit } from '@angular/core';
import { TicketResponse } from "../models/response/ticketResponse";
import { ActivatedRoute, Router } from "@angular/router";
import { TicketPricingType } from "../models/ticketPricingType";
import { TicketType } from "../models/ticketType";

@Component({
  selector: 'app-browse-tickets',
  templateUrl: './browse-tickets.component.html',
  styleUrls: ['./browse-tickets.component.scss']
})
export class BrowseTicketsComponent implements OnInit {
  tickets: TicketResponse[];
  TicketPricingType = TicketPricingType;
  ticketPricingType: TicketPricingType;

  constructor(private activatedRoute: ActivatedRoute,
              private router: Router) {
   this.tickets = this.activatedRoute.snapshot.data['tickets'];
   this.ticketPricingType = TicketPricingType.REGULAR;
  }

  getSingeTickets(): TicketResponse[] {
    return this.tickets?.filter(t => t.ticketType == TicketType.SINGLE)
  }

  getShortTermTickets(): TicketResponse[] {
    return this.tickets?.filter(t => t.ticketType == TicketType.SHORT_TERM)
  }

  getLongTermTickets(): TicketResponse[] {
    return this.tickets?.filter(t => t.ticketType == TicketType.LONG_TERM)
  }

  ngOnInit(): void {
  }

  buyTicketClicked(ticket: TicketResponse): void {
    this.router.navigate(["/buyTicket"],{queryParams: {ticketId: ticket.id, pricingType: this.ticketPricingType}});
  }

}
