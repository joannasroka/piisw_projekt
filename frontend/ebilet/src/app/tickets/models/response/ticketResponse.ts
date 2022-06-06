import { TicketType } from "../ticketType";

export interface TicketResponse {
  id: number,
  normalPrice: number,
  reducedPrice: number,
  name: string,
  ticketType: TicketType,
  minutes?: number,
  hours?: number,
  days?: number,
}
