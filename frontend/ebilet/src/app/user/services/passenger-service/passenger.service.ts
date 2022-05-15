import { Injectable } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PassengerSignupResponse } from "../../models/response/passengerSignupResponse";
import { PassengerSingupRequest } from "../../models/request/passengerSingupRequest";

@Injectable({
  providedIn: 'root'
})
export class PassengerService {

  private readonly apiURL = `${environment.apiUrl}/api/passengers`;

  constructor(
    private http: HttpClient
  ) { }

  signup(passengerSignupRequest: PassengerSingupRequest): Observable<PassengerSignupResponse> {
    return this.http.post<PassengerSignupResponse>(`${this.apiURL}`, passengerSignupRequest);
  }
}
