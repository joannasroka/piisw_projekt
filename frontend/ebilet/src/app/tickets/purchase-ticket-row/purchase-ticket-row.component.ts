import { Component, Input, OnInit } from '@angular/core';
import { PurchaseTicketResponse } from "../models/response/purchaseTicketResponse";
import { TicketPricingType } from "../models/ticketPricingType";
import { PurchaseTicketStatus } from "../models/purchaseTicketStatus";
import { DetailData } from "../../shared/details-table/DetailData";
import { TicketType } from "../models/ticketType";
import { CurrencyPipe } from "@angular/common";
import { LayoutService } from 'src/app/shared/layout-service/layout.service';
import { Observable } from "rxjs";

@Component({
  selector: 'app-purchase-ticket-row',
  templateUrl: './purchase-ticket-row.component.html',
  styleUrls: ['./purchase-ticket-row.component.scss']
})
export class PurchaseTicketRowComponent implements OnInit {
  @Input() purchasedTicket!: PurchaseTicketResponse;
  validationDetails: DetailData[] = [];
  purchasedTicketDetails: DetailData[] = [];
  pricingTypeString: string = "";
  pricingTypeShortString: string = "";
  TicketPricingType = TicketPricingType;

  isHandset$: Observable<boolean>;

  constructor(
    private currencyPipe: CurrencyPipe,
    private layoutService: LayoutService
  ) {
    this.isHandset$ = this.layoutService.isHandset$;
  }

  ngOnInit(): void {
    let ticketTypeString;
    let priceString;

    if (this.purchasedTicket?.price==TicketPricingType.REGULAR) {
      this.pricingTypeString = "Regular";
      this.pricingTypeShortString = "R";
      priceString = <string>this.currencyPipe.transform(this.purchasedTicket?.ticket.normalPrice);
    } else {
      this.pricingTypeString = "Reduced-price";
      this.pricingTypeShortString = "R-p";
      priceString = <string>this.currencyPipe.transform(this.purchasedTicket?.ticket.reducedPrice);
    }

    switch (this.purchasedTicket?.ticket.ticketType) {
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

    this.purchasedTicketDetails = [
      {detailName: "Ticket name", detailData: this.purchasedTicket?.ticket.name},
      {detailName: "Ticket type", detailData: ticketTypeString},
      {detailName: "Pricing type", detailData: this.pricingTypeString},
      {detailName: "Price", detailData: priceString},
      {detailName: "Date of purchase", detailData: new Date(this.purchasedTicket?.dateOfPurchase).toLocaleString('en-US')},
    ]

    this.validationDetails = [
      {detailName: "Verification code", detailData: this.purchasedTicket?.id.toString()},
    ]

    //for ACTIVE and INVALID tickets we display validity info (current or past)
    if (this.isActive() || this.isInvalid()) {
      switch (this.purchasedTicket.ticket.ticketType) {
        case TicketType.SINGLE: case TicketType.SHORT_TERM: {
          this.validationDetails.push({detailName: "Valid through:", detailData: this.getValidThroughLongDate()})
          this.validationDetails.push({detailName: "Date of validation:", detailData: this.getValidationDate()})
          break;
        }
        case TicketType.LONG_TERM: {
          this.validationDetails.push({detailName: "Valid from:", detailData: this.getValidFromShortDate()})
          this.validationDetails.push({detailName: "Valid through:", detailData: this.getValidThroughShortDate()})
          break;
        }
      }
    }
  }

  getValidationDate(): string {
    return new Date(this.purchasedTicket.dateTimeOfValidation!).toLocaleString('en-US');
  }

  getValidFromLongDate(): string {
    return new Date(this.purchasedTicket.validityStartDateTime!).toLocaleString('en-US');
  }

  getValidFromShortDate(): string {
    return new Date(this.purchasedTicket.validityStartDateTime!).toLocaleDateString('en-US');
  }

  getValidThroughLongDate(): string {
    return new Date(this.purchasedTicket.validityEndDateTime!).toLocaleString('en-US');
  }

  getValidThroughShortDate(): string {
    return new Date(this.purchasedTicket.validityEndDateTime!).toLocaleDateString('en-US');
  }

  isActive(): boolean {
    return this.purchasedTicket?.ticketPurchaseStatus == PurchaseTicketStatus.ACTIVE
  }

  isInactive(): boolean {
    return this.purchasedTicket?.ticketPurchaseStatus == PurchaseTicketStatus.INACTIVE
  }

  isInvalid(): boolean {
    return this.purchasedTicket?.ticketPurchaseStatus == PurchaseTicketStatus.INVALID
  }
}
