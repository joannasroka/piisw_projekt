import { Injectable } from '@angular/core';
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable } from 'rxjs';
import { TicketService } from "../../services/ticket-service/ticket.service";
import { TicketResponse } from "../../models/response/ticketResponse";

@Injectable({
  providedIn: 'root'
})
export class BuyTicketResolver implements Resolve<TicketResponse> {

  constructor(private readonly ticketService: TicketService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TicketResponse> {
    return this.ticketService.getTicketById(route.queryParamMap.get("ticketId") ?? "");
  }
}
