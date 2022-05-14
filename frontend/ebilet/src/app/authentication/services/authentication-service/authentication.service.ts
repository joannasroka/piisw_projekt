import { Injectable } from '@angular/core';
import { UserAuth } from "../../models/userAuth";
import { BehaviorSubject, Observable, switchMap } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { map } from "rxjs/operators";
import { environment } from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private userAuthSubject: BehaviorSubject<UserAuth>;
  public userAuthObservable: Observable<UserAuth>;

  constructor(
    private http: HttpClient,
  ) {
    this.userAuthSubject = new BehaviorSubject<any>(null);
    this.userAuthObservable = this.userAuthSubject.asObservable();
  }

  readStoredUserAuth(): void {
    const savedUserAuth = sessionStorage.getItem('userAuth')
    if (savedUserAuth) {
      this.userAuthSubject.next(JSON.parse(savedUserAuth))
    }
  }

  login(username: string, password: string): Observable<UserAuth> {
    return this.http.post<any>(`${environment.apiUrl}/login`, {},{params: {username, password}, withCredentials: true})
      .pipe(
        switchMap(() => {
          return this.http.get<UserAuth>(`${environment.apiUrl}/api/users/@me`, {withCredentials: true})
            .pipe(map(user => {
              this.userAuthSubject.next(user);
              sessionStorage.setItem('userAuth', JSON.stringify(user))
              console.log(user);
              return user;
            }));
        })
      );
  }
}
