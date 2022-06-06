import { Component } from '@angular/core';
import { Observable } from "rxjs";
import { UserAuth } from "./authentication/models/userAuth";
import { UserRole } from "./authentication/models/userRole";
import { AuthenticationService } from './authentication/services/authentication-service/authentication.service';
import { Router } from "@angular/router";
import { SnackbarService } from "./shared/snackbar/snackbar-service/snackbar.service";
import { LayoutService } from './shared/layout-service/layout.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  userAuth: UserAuth | undefined;
  readonly UserRole = UserRole;
  loading = false;

  isHandset$: Observable<boolean>;

  constructor(private router: Router,
              private snackbarService: SnackbarService,
              private authenticationService: AuthenticationService,
              private layoutService: LayoutService) {
    this.isHandset$ = this.layoutService.isHandset$;
    this.authenticationService.userAuthObservable.subscribe(x => this.userAuth = x);
    this.authenticationService.readStoredUserAuth();
  }

  get isUserRolePassenger(): boolean {
    return this.userAuth ? this.userAuth.role === UserRole.PASSENGER : false;
  }

  get isUserRoleInspector(): boolean {
    return this.userAuth ? this.userAuth.role === UserRole.INSPECTOR : false;
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
          this.router.navigate(['/']).then(() => this.snackbarService.openErrorSnackbar('Logout error'));
        }
      });
  }
}
