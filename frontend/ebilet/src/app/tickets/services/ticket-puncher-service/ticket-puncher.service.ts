import { Injectable } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PurchaseTicketResponse } from '../../models/response/purchaseTicketResponse';

@Injectable({
  providedIn: 'root'
})
export class TicketPuncherService {

  private readonly apiURL = `${environment.apiUrl}/api/ticket-punchers`;

  constructor(
    private http: HttpClient
  ) { }

  validateTicket(ticketPurchaseId: number): Observable<PurchaseTicketResponse> {
    return this.http.put<PurchaseTicketResponse>(`${this.apiURL}/punch`,{},{withCredentials: true, params: {ticketPurchaseId}});
  }
}
