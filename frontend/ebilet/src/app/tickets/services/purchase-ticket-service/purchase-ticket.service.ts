import { Injectable } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PurchaseTicketRequest } from "../../models/request/purchaseTicketRequest";
import { PurchaseTicketResponse } from "../../models/response/purchaseTicketResponse";

@Injectable({
  providedIn: 'root'
})
export class PurchaseTicketService {

  private readonly apiURL = `${environment.apiUrl}/api/purchase-tickets`;

  constructor(
    private http: HttpClient
  ) { }

  purchaseTicket(purchaseTicketRequest: PurchaseTicketRequest): Observable<PurchaseTicketResponse> {
    return this.http.post<PurchaseTicketResponse>(`${this.apiURL}`, purchaseTicketRequest, {withCredentials: true});
  }

  getPurchasedTickets(): Observable<PurchaseTicketResponse[]> {
    return this.http.get<PurchaseTicketResponse[]>(`${this.apiURL}`, {withCredentials: true});
  }
}
