import { Injectable } from '@angular/core';
import { environment } from "../../../../environments/environment";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { PassengerSignupResponse } from "../../models/response/passengerSignupResponse";
import { VerifyTokenRequest } from "../../models/request/verifyTokenRequest";

@Injectable({
  providedIn: 'root'
})
export class VerificationTokenService {

  private readonly apiURL = `${environment.apiUrl}/api/tokens`;

  constructor(
    private http: HttpClient
  ) { }

  checkTokenValidity(token: string): Observable<any> {
    return this.http.head<any>(`${this.apiURL}`, {params: {token}});
  }

  verifyToken(verifyTokenRequest: VerifyTokenRequest): Observable<any> {
    return this.http.post<PassengerSignupResponse>(`${this.apiURL}`, verifyTokenRequest);
  }
}
