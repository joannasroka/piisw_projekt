import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { SnackbarService } from 'src/app/shared/snackbar/snackbar-service/snackbar.service';
import { PurchaseTicketResponse } from '../models/response/purchaseTicketResponse';
import { PurchaseTicketService } from "../services/purchase-ticket-service/purchase-ticket.service";
import { PurchaseTicketStatus } from "../models/purchaseTicketStatus";
import { DetailData } from 'src/app/shared/details-table/DetailData';
import { TicketPricingType } from "../models/ticketPricingType";
import { CurrencyPipe } from "@angular/common";
import { TicketType } from "../models/ticketType";

@Component({
  selector: 'app-inspect-tickets',
  templateUrl: './inspect-tickets.component.html',
  styleUrls: ['./inspect-tickets.component.scss']
})
export class InspectTicketsComponent implements OnInit {
  checkTicketForm: FormGroup;
  loading = false;
  isTicketFound: boolean | undefined;
  hasVerificationPassed: boolean | undefined;
  currentTicket: PurchaseTicketResponse | undefined;
  purchasedTicketDetails: DetailData[] = [];
  validationDetails: DetailData[] = [];

  TicketPricingType = TicketPricingType;
  TicketType = TicketType;

  constructor(
    private formBuilder: FormBuilder,
    private purchaseTicketService: PurchaseTicketService,
    private snackbarService: SnackbarService,
    private currencyPipe: CurrencyPipe,
  ) {
    this.checkTicketForm = this.formBuilder.group({
      verificationCode: ['', Validators.required]
    });
  }

  ngOnInit(): void {
  }

  // convince getter for easy access to form fields
  get f(): { [p: string]: AbstractControl } { return this.checkTicketForm.controls; }

  checkTicket(): void {
    this.loading = true;
    this.isTicketFound = false;
    this.hasVerificationPassed = undefined;
    this.purchaseTicketService.getPurchasedTicketForVerificationCode(this.f['verificationCode'].value)
      .subscribe({
        next: (inspectedTicket: PurchaseTicketResponse) => {
          this.isTicketFound = true;
          this.hasVerificationPassed = inspectedTicket.ticketPurchaseStatus == PurchaseTicketStatus.ACTIVE;
          this.currentTicket = inspectedTicket;
          this.loadTicketDetails();
          console.log(this.purchasedTicketDetails);
          this.loading = false;
        },
        error: (err) => {
          if (err.error.globalException.message == "error.resourceNotFound") {
            this.isTicketFound = false;
            this.hasVerificationPassed = undefined;
          } else {
            this.snackbarService.openErrorSnackbar('There was an error while checking the ticket!');
          }
          this.loading = false;
        }
      })
  }

  shouldAdditionalCheck(): boolean {
    return this.currentTicket?.ticket.ticketType == TicketType.SINGLE || this.currentTicket?.price == TicketPricingType.REDUCED;
  }

  loadTicketDetails(): void {
    let ticketTypeString;
    let ticketStatusString;
    let pricingTypeString;
    let priceString;

    if (this.currentTicket!.price==TicketPricingType.REGULAR) {
      pricingTypeString = "Regular";
      priceString = <string>this.currencyPipe.transform(this.currentTicket!.ticket.normalPrice);
    } else {
      pricingTypeString = "Reduced-price";
      priceString = <string>this.currencyPipe.transform(this.currentTicket!.ticket.reducedPrice);
    }

    switch (this.currentTicket!.ticket.ticketType) {
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

    switch (this.currentTicket!.ticketPurchaseStatus) {
      case PurchaseTicketStatus.INACTIVE: {
        ticketStatusString = "Inactive";
        break;
      }
      case PurchaseTicketStatus.ACTIVE: {
        ticketStatusString = "Active";
        break;
      }
      case PurchaseTicketStatus.INVALID: {
        ticketStatusString = "Invalid";
        break;
      }
    }

    this.purchasedTicketDetails = [
      {detailName: "Ticket name", detailData: this.currentTicket!.ticket.name},
      {detailName: "Ticket type", detailData: ticketTypeString},
      {detailName: "Pricing type", detailData: pricingTypeString},
      {detailName: "Price", detailData: priceString},
      {detailName: "Date of purchase", detailData: new Date(this.currentTicket!.dateOfPurchase).toLocaleString('en-US')},
    ]

    this.validationDetails = [
      {detailName: "Verification code", detailData: this.currentTicket!.globalId.toString()},
      {detailName: "Ticket status", detailData: ticketStatusString}
    ]

    switch (this.currentTicket!.ticket.ticketType) {
      case TicketType.SINGLE: {
        if(this.currentTicket!.ticketPurchaseStatus !== PurchaseTicketStatus.INACTIVE) {
          this.validationDetails.push({detailName: "Date of validation:", detailData: this.getValidationDate()})
          this.validationDetails.push({detailName: "Valid through:", detailData: "Until end of the line"})
        }
        break;
      }
      case TicketType.SHORT_TERM: {
        if(this.currentTicket!.ticketPurchaseStatus !== PurchaseTicketStatus.INACTIVE) {
          this.validationDetails.push({detailName: "Date of validation:", detailData: this.getValidationDate()})
          this.validationDetails.push({detailName: "Valid through:", detailData: this.getValidThroughLongDate()})
        }
        break;
      }
      case TicketType.LONG_TERM: {
        this.validationDetails.push({detailName: "Valid from:", detailData: this.getValidFromShortDate()})
        this.validationDetails.push({detailName: "Valid through:", detailData: this.getValidThroughShortDate()})
        break;
      }
    }
  }

  getValidationDate(): string {
    return new Date(this.currentTicket!.dateTimeOfValidation!).toLocaleString('en-US');
  }

  getValidFromLongDate(): string {
    return new Date(this.currentTicket!.validityStartDateTime!).toLocaleString('en-US');
  }

  getValidFromShortDate(): string {
    return new Date(this.currentTicket!.validityStartDateTime!).toLocaleDateString('en-US');
  }

  getValidThroughLongDate(): string {
    return new Date(this.currentTicket!.validityEndDateTime!).toLocaleString('en-US');
  }

  getValidThroughShortDate(): string {
    return new Date(this.currentTicket!.validityEndDateTime!).toLocaleDateString('en-US');
  }

}
