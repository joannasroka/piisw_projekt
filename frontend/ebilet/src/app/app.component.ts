import { Component } from '@angular/core';
import { Observable } from "rxjs";
import { BreakpointObserver, Breakpoints } from "@angular/cdk/layout";
import { map, shareReplay } from "rxjs/operators";
import { UserAuth } from "./authentication/models/userAuth";
import { AuthenticationService } from './authentication/services/authentication-service/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  userAuth: UserAuth | undefined;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,
              private authenticationService: AuthenticationService) {
    this.authenticationService.userAuthObservable.subscribe(x => this.userAuth = x);
    this.authenticationService.readStoredUserAuth();
  }
}
