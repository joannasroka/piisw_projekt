import { Injectable } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { TicketResponse } from "../../models/response/ticketResponse";

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  readonly apiURL = `${environment.apiUrl}/api/tickets`;

  constructor(
    private http: HttpClient
  ) { }

  getTickets(): Observable<TicketResponse[]> {
    return this.http.get<TicketResponse[]>(`${this.apiURL}`, {withCredentials: true});
  }

  getTicketById(ticketId: string): Observable<TicketResponse> {
    return this.http.get<TicketResponse>(`${this.apiURL}/${ticketId}`, {withCredentials: true});
  }
}
