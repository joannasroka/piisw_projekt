import { TicketType } from "./ticketType";

export interface TicketResponse {
  id: number,
  normalPrice: number,
  reducedPrice: number,
  name: string,
  ticketType: TicketType,
  minutes: number | null,
  hours: number | null,
  days: number | null,
}
