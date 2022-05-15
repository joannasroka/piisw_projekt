import { Component } from '@angular/core';
import { Observable } from "rxjs";
import { BreakpointObserver, Breakpoints } from "@angular/cdk/layout";
import { map, shareReplay } from "rxjs/operators";
import { UserAuth } from "./authentication/models/userAuth";
import { AuthenticationService } from './authentication/services/authentication-service/authentication.service';
import { Router } from "@angular/router";
import { SnackbarService } from "./shared/snackbar/snackbar-service/snackbar.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  userAuth: UserAuth | undefined;
  loading = false;

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,
              private router: Router,
              private snackbarService: SnackbarService,
              private authenticationService: AuthenticationService) {
    this.authenticationService.userAuthObservable.subscribe(x => this.userAuth = x);
    this.authenticationService.readStoredUserAuth();
  }

  logout(): void {
    this.loading = true;
    this.authenticationService.logout()
      .subscribe({
        next: () => {
          this.loading = false;
          this.router.navigate(['/']).then(() => this.snackbarService.openSuccessSnackbar('Logged out'));
        },
        error: () => {
          this.loading = false;
          this.snackbarService.openErrorSnackbar('Logout error!');
        }
      });
  }
}
