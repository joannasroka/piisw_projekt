import { Injectable } from '@angular/core';
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot
} from '@angular/router';
import { Observable } from 'rxjs';
import { PurchaseTicketService } from '../../services/purchase-ticket-service/purchase-ticket.service';
import { PurchaseTicketResponse } from "../../models/response/purchaseTicketResponse";

@Injectable({
  providedIn: 'root'
})
export class MyTicketsResolver implements Resolve<PurchaseTicketResponse[]> {

  constructor(private readonly purchaseTicketService: PurchaseTicketService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<PurchaseTicketResponse[]> {
    return this.purchaseTicketService.getPurchasedTickets();
  }
}
