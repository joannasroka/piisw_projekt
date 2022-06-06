import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { TicketResponse } from "../models/response/ticketResponse";
import { TicketPricingType } from '../models/ticketPricingType';
import { DetailData } from "../../shared/details-table/DetailData";
import { CurrencyPipe } from "@angular/common";
import { TicketType } from "../models/ticketType";
import { MatStepper } from "@angular/material/stepper";
import { DialogService } from "../../shared/dialog/dialog-service/dialog.service";
import { PurchaseTicketService } from '../services/purchase-ticket-service/purchase-ticket.service';
import { PurchaseTicketRequest } from "../models/request/purchaseTicketRequest";
import { FormControl } from '@angular/forms';
import { LayoutService } from "../../shared/layout-service/layout.service";
import { Observable } from "rxjs";
import { SnackbarService } from "../../shared/snackbar/snackbar-service/snackbar.service";

@Component({
  selector: 'app-buy-ticket',
  templateUrl: './buy-ticket.component.html',
  styleUrls: ['./buy-ticket.component.scss']
})
export class BuyTicketComponent implements OnInit {
  TicketType = TicketType;
  isPurchaseCompleted: boolean = false;
  ticketToBuy: TicketResponse;
  ticketDetails: DetailData[] = [];

  pricingType: TicketPricingType;

  priceString: string = "";
  pricingTypeString: string = "";

  loading = false;
  @ViewChild('stepper') private stepper!: MatStepper;

  todayDate: Date = new Date();
  dateFormControl = new FormControl();

  isHandset$: Observable<boolean>;

  purchasedTicketValidationCode: string = "";

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private currencyPipe: CurrencyPipe,
    private dialogService: DialogService,
    private layoutService: LayoutService,
    private snackbarService: SnackbarService,
    private purchaseTicketService: PurchaseTicketService
  ) {
    this.ticketToBuy = this.activatedRoute.snapshot.data['ticketToBuy'];
    this.pricingType = <TicketPricingType>this.activatedRoute.snapshot.queryParamMap.get("pricingType");
    this.isHandset$ = this.layoutService.isHandset$;
  }

  ngOnInit(): void {
    this.setDetailsTable();
  }

  setDetailsTable(): void {
    let ticketTypeString;
    if (this.pricingType==TicketPricingType.REGULAR) {
      this.pricingTypeString = "Regular";
      this.priceString = <string>this.currencyPipe.transform(this.ticketToBuy.normalPrice);
    } else {
      this.pricingTypeString = "Reduced-price";
      this.priceString = <string>this.currencyPipe.transform(this.ticketToBuy.reducedPrice);
    }
    switch (this.ticketToBuy.ticketType) {
      case TicketType.SINGLE: {
        ticketTypeString = "Single ticket";
        break;
      }
      case TicketType.SHORT_TERM: {
        ticketTypeString = "Short-term ticket";
        break;
      }
      case TicketType.LONG_TERM: {
        ticketTypeString = "Long-term ticket";
        break;
      }
    }
    this.ticketDetails = [
      {detailName: "Ticket name:", detailData: this.ticketToBuy.name},
      {detailName: "Ticket type:", detailData: ticketTypeString},
      {detailName: "Pricing type:", detailData: this.pricingTypeString},
      {detailName: "Price:", detailData: this.priceString},
    ]
  }

  //TODO: Fix date input field locale to current browser locale
  getValidThroughDate(): string {
    const validThroughDate = new Date(this.dateFormControl.value);
    validThroughDate.setDate(validThroughDate.getDate() + this.ticketToBuy.days!);
    return validThroughDate.toLocaleDateString();
  }

  getValidFromDate(): string {
    return this.dateFormControl.value.toLocaleDateString();
  }

  getValidityStartDateForRequest(): string {
    let startDate = this.dateFormControl.value;
    const offset = startDate.getTimezoneOffset()
    startDate = new Date(startDate.getTime() - (offset*60*1000))
    return startDate.toISOString().split('T')[0]
  }

  promptForPurchaseConfirmation(): void {
    let ticketValidityInfoString = "";
    if (this.ticketToBuy.ticketType == TicketType.LONG_TERM) {
      ticketValidityInfoString = ` valid from ${this.getValidFromDate()} to ${this.getValidThroughDate()} `
    }
    this.dialogService.openYesNoDialog("Do you really want to buy this ticket?",
      `You're about to buy ${this.ticketToBuy.name} (${this.pricingTypeString}) ${ticketValidityInfoString} for ${this.priceString}.`)
      .beforeClosed().subscribe(result => {
        if (result) {
          this.buyTicket();
        }
    });
  }

  buyTicket(): void {
    this.loading = true;

    let purchaseTicketRequest: PurchaseTicketRequest = {
      ticketId: this.ticketToBuy.id,
      ticketPrice: this.pricingType,
    }

    if (this.ticketToBuy.ticketType == TicketType.LONG_TERM) {
      purchaseTicketRequest.validityStartDate = this.getValidityStartDateForRequest();
    }

    this.purchaseTicketService.purchaseTicket(purchaseTicketRequest)
      .subscribe({
        next: (purchaseTicketResponse) => {
          console.log(purchaseTicketResponse);
          this.purchasedTicketValidationCode = purchaseTicketResponse.id.toString();
          this.snackbarService.openSuccessSnackbar("You've successfully purchased a ticket!");
          this.isPurchaseCompleted = true;
          this.loading = false;
          this.stepper.next();
        },
        error: () => {
          this.router.navigate(['/browseTickets']).then(() => this.snackbarService.openErrorSnackbar('There was an error while purchasing the ticket!'));
        }
      })
  }


}
