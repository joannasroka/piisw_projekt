import { TicketType } from "../ticketType";

export interface TicketResponse {
  globalId: string,
  normalPrice: number,
  reducedPrice: number,
  name: string,
  ticketType: TicketType,
  minutes?: number,
  hours?: number,
  days?: number,
}
