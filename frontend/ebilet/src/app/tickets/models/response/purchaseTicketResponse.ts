import { TicketResponse } from "./ticketResponse";
import { TicketPricingType } from "../ticketPricingType";

export interface PurchaseTicketResponse {
  id: number,
  ticket: TicketResponse,
  price: TicketPricingType,
  dateOfPurchase: Date,
  dateTimeOfValidation: Date,
  validityStartDateTime: Date,
  valid: boolean
}
