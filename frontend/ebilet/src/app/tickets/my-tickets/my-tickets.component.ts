import { Component, OnInit } from '@angular/core';
import { PurchaseTicketResponse } from "../models/response/purchaseTicketResponse";
import { ActivatedRoute } from "@angular/router";
import { SelectedTicketView } from "../models/selectedTicketView";
import { PurchaseTicketStatus } from "../models/purchaseTicketStatus";
import { DialogService } from 'src/app/shared/dialog/dialog-service/dialog.service';
import { TicketPricingType } from "../models/ticketPricingType";
import { TicketPuncherService } from '../services/ticket-puncher-service/ticket-puncher.service';
import { SnackbarService } from 'src/app/shared/snackbar/snackbar-service/snackbar.service';
import { PurchaseTicketService } from "../services/purchase-ticket-service/purchase-ticket.service";
import { TicketType } from "../models/ticketType";

@Component({
  selector: 'app-my-tickets',
  templateUrl: './my-tickets.component.html',
  styleUrls: ['./my-tickets.component.scss']
})
export class MyTicketsComponent implements OnInit {
  myTickets!: PurchaseTicketResponse[];
  loading: boolean = false;
  SelectedTicketView = SelectedTicketView;
  selectedTicketView: SelectedTicketView = SelectedTicketView.CURRENT;

  fastestEndingTicket: PurchaseTicketResponse | undefined;
  ticketEndTimeout: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private dialogService: DialogService,
    private ticketPuncherService: TicketPuncherService,
    private snackbarService: SnackbarService,
    private purchaseTicketService: PurchaseTicketService
  ) {
    this.myTickets = this.activatedRoute.snapshot.data['myTickets'];
    this.setTimerForFastestEndingNonSingleTicket();
  }

  ngOnInit(): void {
  }

  getActiveTickets(): PurchaseTicketResponse[] {
    // sorted by remaining validity
    return this.myTickets?.filter((ticket: PurchaseTicketResponse) => ticket.ticketPurchaseStatus == PurchaseTicketStatus.ACTIVE)
      .sort((ticketA: PurchaseTicketResponse, ticketB: PurchaseTicketResponse) => new Date(ticketA.validityEndDateTime!).getTime() - new Date(ticketB.validityEndDateTime!).getTime())
  }

  getInactiveTickets(): PurchaseTicketResponse[] {
    // sorted by date of purchase (latest purchased tickets on top)
    return this.myTickets?.filter((ticket: PurchaseTicketResponse) => ticket.ticketPurchaseStatus == PurchaseTicketStatus.INACTIVE)
      .sort((ticketA: PurchaseTicketResponse, ticketB: PurchaseTicketResponse) => new Date(ticketB.dateOfPurchase).getTime() - new Date(ticketA.dateOfPurchase).getTime());
  }

  getHistoryTickets(): PurchaseTicketResponse[] {
    // sorted by date of purchase (latest purchased tickets on top)
    return this.myTickets?.filter((ticket: PurchaseTicketResponse) => ticket.ticketPurchaseStatus == PurchaseTicketStatus.INVALID)
      .sort((ticketA: PurchaseTicketResponse, ticketB: PurchaseTicketResponse) => new Date(ticketB.dateOfPurchase).getTime() - new Date(ticketA.dateOfPurchase).getTime());
  }

  setTimerForFastestEndingNonSingleTicket(): void {
    this.fastestEndingTicket = this.findFastestEndingNonSingleTicket();
    if (this.fastestEndingTicket) {
      const timeout = new Date(this.fastestEndingTicket.validityEndDateTime!).getTime() - Date.now();
      this.ticketEndTimeout = setTimeout(() => {
        this.reloadTickets()
      }, timeout);
    }
  }

  findFastestEndingNonSingleTicket(): PurchaseTicketResponse | undefined {
    return this.getActiveTickets()?.filter((ticket: PurchaseTicketResponse) => ticket.ticket.ticketType !== TicketType.SINGLE)[0];
  }

  reloadTickets(): void {
    this.loading = true;
    this.purchaseTicketService.getPurchasedTickets()
      .subscribe({
      next: (purchasedTickets) => {
        this.myTickets = purchasedTickets;
        this.setTimerForFastestEndingNonSingleTicket();
        this.loading = false;
      },
      error: () => {
        this.snackbarService.openErrorSnackbar('There was an error while downloading your ticket!');
        this.loading = false;
      }
    })
  }

  promptForValidateConfirmation(ticketToValidate: PurchaseTicketResponse): void {
    let pricingTypeString;
    if (ticketToValidate.price==TicketPricingType.REGULAR) {
      pricingTypeString = "Regular";
    } else {
      pricingTypeString = "Reduced-price";
    }
    this.dialogService.openYesNoDialog("Do you really want to validate this ticket?",
      `You're about to validate ${ticketToValidate.ticket.name} (${pricingTypeString}).`)
      .beforeClosed().subscribe(result => {
      if (result) {
        this.validateTicket(ticketToValidate.id);
      }
    });
  }

  validateTicket(ticketId: number): void {
    this.loading = true;

    this.ticketPuncherService.validateTicket(ticketId)
      .subscribe({
        next: () => {
          this.snackbarService.openSuccessSnackbar("You've successfully validated a ticket!");
          this.reloadTickets();
        },
        error: () => {
          this.snackbarService.openErrorSnackbar('There was an error while validating the ticket!');
          this.loading = false;
        }
      })
  }

}
