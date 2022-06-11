import { Component, OnInit } from '@angular/core';
import { PurchaseTicketResponse } from "../models/response/purchaseTicketResponse";
import { ActivatedRoute } from "@angular/router";
import { SelectedTicketView } from "../models/selectedTicketView";
import { PurchaseTicketStatus} from "../models/purchaseTicketStatus";

@Component({
  selector: 'app-my-tickets',
  templateUrl: './my-tickets.component.html',
  styleUrls: ['./my-tickets.component.scss']
})
export class MyTicketsComponent implements OnInit {
  myTickets!: PurchaseTicketResponse[];
  SelectedTicketView = SelectedTicketView;
  selectedTicketView: SelectedTicketView = SelectedTicketView.CURRENT;

  constructor(
    private activatedRoute: ActivatedRoute,
  ) {
    this.myTickets = this.activatedRoute.snapshot.data['myTickets'];
  }

  ngOnInit(): void {
  }

  getActiveTickets(): PurchaseTicketResponse[] {
    return this.myTickets?.filter((ticket: PurchaseTicketResponse) => ticket.ticketPurchaseStatus == PurchaseTicketStatus.ACTIVE);
  }

  getInactiveTickets(): PurchaseTicketResponse[] {
    return this.myTickets?.filter((ticket: PurchaseTicketResponse) => ticket.ticketPurchaseStatus == PurchaseTicketStatus.INACTIVE);
  }

  getHistoryTickets(): PurchaseTicketResponse[] {
    return this.myTickets?.filter((ticket: PurchaseTicketResponse) => ticket.ticketPurchaseStatus == PurchaseTicketStatus.INVALID);
  }

}
