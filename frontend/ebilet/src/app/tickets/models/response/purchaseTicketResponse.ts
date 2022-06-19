import { TicketResponse } from "./ticketResponse";
import { TicketPricingType } from "../ticketPricingType";
import { PurchaseTicketStatus } from "../purchaseTicketStatus";

export interface PurchaseTicketResponse {
  globalId: string,
  ticket: TicketResponse,
  price: TicketPricingType,
  dateOfPurchase: Date,
  ticketPurchaseStatus: PurchaseTicketStatus,
  //only for SINGLE and SHORT-TERM tickets
  dateTimeOfValidation?: Date,
  //only for LONG-TERM tickets
  validityStartDateTime?: Date,
  //only for ACTIVE tickets
  validityEndDateTime?: Date
}
