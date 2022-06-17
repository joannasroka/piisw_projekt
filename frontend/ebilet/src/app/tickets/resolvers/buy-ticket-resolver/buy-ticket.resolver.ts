import { Injectable } from '@angular/core';
import {
  Resolve,
  RouterStateSnapshot,
  ActivatedRouteSnapshot, Router
} from '@angular/router';
import { catchError, EMPTY, Observable } from 'rxjs';
import { TicketService } from "../../services/ticket-service/ticket.service";
import { TicketResponse } from "../../models/response/ticketResponse";
import { SnackbarService } from 'src/app/shared/snackbar/snackbar-service/snackbar.service';
import { TicketPricingType } from "../../models/ticketPricingType";

@Injectable({
  providedIn: 'root'
})
export class BuyTicketResolver implements Resolve<TicketResponse> {

  constructor(private readonly ticketService: TicketService,
              private readonly router: Router,
              private readonly snackbarService: SnackbarService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<TicketResponse> {
    let routePricingType = route.queryParamMap.get("pricingType");
    if (routePricingType && !Object.values<string>(TicketPricingType).includes(routePricingType)) {
      this.navigateAndError();
      return EMPTY;
    }
    return this.ticketService.getTicketById(route.queryParamMap.get("ticketId") ?? "")
      .pipe(
        catchError(() => {
          this.navigateAndError();
          return EMPTY;
        })
      );
  }

  navigateAndError() : void{
    this.router.navigate(["/browseTickets"]).then(() => this.snackbarService.openErrorSnackbar('There was an error while retrieving the ticket data!'));
  }
}
