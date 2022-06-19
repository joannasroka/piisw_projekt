import { TicketPricingType } from "../ticketPricingType";

export interface PurchaseTicketRequest {
  globalId: string,
  ticketPrice: TicketPricingType,
  validityStartDate?: string
}
