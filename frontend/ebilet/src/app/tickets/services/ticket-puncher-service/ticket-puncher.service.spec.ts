import { TestBed } from '@angular/core/testing';

import { TicketPuncherService } from './ticket-puncher.service';
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { PurchaseTicketResponse } from "../../models/response/purchaseTicketResponse";
import { TicketPricingType } from "../../models/ticketPricingType";
import { TicketType } from "../../models/ticketType";
import { PurchaseTicketStatus } from "../../models/purchaseTicketStatus";

describe('TicketPuncherService', () => {
  let service: TicketPuncherService;
  let httpMock: HttpTestingController;
  let testPurchaseTicketResponse: PurchaseTicketResponse = {
    dateOfPurchase: new Date(),
    globalId: "1",
    price: TicketPricingType.REGULAR,
    ticket: {
      globalId: "1",
      normalPrice: 1,
      reducedPrice: 0.5,
      name: "Test ticket",
      ticketType: TicketType.SINGLE,
    },
    ticketPurchaseStatus: PurchaseTicketStatus.INACTIVE
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
    });
    service = TestBed.inject(TicketPuncherService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('validateTicket()', () => {
    it('should call /punch endpoint using PUT', () => {
      service.validateTicket("1").subscribe();

      const httpRequest = httpMock.expectOne(`${service.apiURL}/punch?ticketPurchaseGlobalId=1`);
      expect(httpRequest.request.method).toBe("PUT");
      httpRequest.flush(testPurchaseTicketResponse);
    });
  });
});
