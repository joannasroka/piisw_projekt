import { TicketPricingType } from "../ticketPricingType";

export interface PurchaseTicketRequest {
  ticketId: number,
  ticketPrice: TicketPricingType,
  validityStartDate?: string
}
