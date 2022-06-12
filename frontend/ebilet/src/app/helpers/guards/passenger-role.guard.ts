import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { SnackbarService } from "../../shared/snackbar/snackbar-service/snackbar.service";
import { AuthenticationService } from "../../authentication/services/authentication-service/authentication.service";
import { UserRole } from "../../authentication/models/userRole";

@Injectable({
  providedIn: 'root'
})
export class PassengerRoleGuard implements CanActivate {
  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private authenticationService: AuthenticationService
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const user = this.authenticationService.userAuthValue();
    if (user.role == UserRole.PASSENGER) {
      // logged in so return true
      return true;
    } else {
      // not logged in so redirect to login page with return url
      this.router.navigate(['/landingPage']).then(() => this.snackbarService.openErrorSnackbar('You cannot access this page as inspector!'));
      return false;
    }
  }
}
