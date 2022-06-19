import { TicketPricingType } from "../ticketPricingType";

export interface PurchaseTicketRequest {
  ticketId: string,
  ticketPrice: TicketPricingType,
  validityStartDate?: string
}
