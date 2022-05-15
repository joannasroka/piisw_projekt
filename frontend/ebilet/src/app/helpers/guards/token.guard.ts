import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SnackbarService } from '../../shared/snackbar/snackbar-service/snackbar.service';
import { VerificationTokenService } from "../../user/services/verification-token-service/verification-token.service";

@Injectable({
  providedIn: 'root'
})
export class TokenGuard implements CanActivate {
  constructor(
    private router: Router,
    private snackbarService: SnackbarService,
    private verificationTokenService: VerificationTokenService
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const token = route.queryParams['token'];
    if (!token) {
      this.router.navigate(['/']).then(() => this.snackbarService.openErrorSnackbar('Incorrect activation link!'));
      return false;
    } else {
      this.verificationTokenService.checkTokenValidity(token)
        .subscribe({
          next: () => {
            return true;
          },
          error: () => {
            this.router.navigate(['/']).then(() => this.snackbarService.openErrorSnackbar('Expired activation link!'));
            return false;
          }
        });
    }

    return true;
  }

}
