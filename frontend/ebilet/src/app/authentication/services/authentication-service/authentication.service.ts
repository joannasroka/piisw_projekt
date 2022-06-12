import { Injectable } from '@angular/core';
import { UserAuth } from "../../models/userAuth";
import { BehaviorSubject, Observable, switchMap } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";
import { map } from "rxjs/operators";
import { environment } from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private userAuthSubject: BehaviorSubject<UserAuth | any>;
  public userAuthObservable: Observable<UserAuth | any>;

  constructor(
    private http: HttpClient,
  ) {
    this.userAuthSubject = new BehaviorSubject<any>(null);
    this.userAuthObservable = this.userAuthSubject.asObservable();
  }

  public userAuthValue(): UserAuth {
    return this.userAuthSubject.value;
  }

  readStoredUserAuth(): void {
    const savedUserAuth = sessionStorage.getItem('userAuth')
    if (savedUserAuth) {
      this.userAuthSubject.next(JSON.parse(savedUserAuth))
    }
  }

  login(username: string, password: string): Observable<UserAuth> {
    let body = new HttpParams({fromObject: {username, password}});
    console.log(body.toString());
    return this.http.post<any>(`${environment.apiUrl}/api/login`, body.toString(),
      {headers: {'Content-Type': 'application/x-www-form-urlencoded'}, withCredentials: true})
      .pipe(
        switchMap(() => {
          return this.http.get<UserAuth>(`${environment.apiUrl}/api/users/@me`, {withCredentials: true})
            .pipe(map(user => {
              this.userAuthSubject.next(user);
              sessionStorage.setItem('userAuth', JSON.stringify(user))
              return user;
            }));
        })
      );
  }

  logout(): Observable<any> {
    sessionStorage.clear();
    this.userAuthSubject.next(null);
    return this.http.post<any>(`${environment.apiUrl}/api/logout`, {},{withCredentials: true})
  }
}
