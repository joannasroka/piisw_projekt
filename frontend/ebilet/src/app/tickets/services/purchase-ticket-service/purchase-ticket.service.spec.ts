import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { PurchaseTicketService } from './purchase-ticket.service';
import { PurchaseTicketRequest } from '../../models/request/purchaseTicketRequest';
import { TicketPricingType } from "../../models/ticketPricingType";
import { PurchaseTicketResponse } from "../../models/response/purchaseTicketResponse";
import { TicketType } from "../../models/ticketType";
import { PurchaseTicketStatus } from "../../models/purchaseTicketStatus";

describe('PurchaseTicketService', () => {
  let service: PurchaseTicketService;
  let httpMock: HttpTestingController;
  let testPurchaseTicketRequest: PurchaseTicketRequest = {
    ticketId: 0,
    ticketPrice: TicketPricingType.REGULAR
  }

  let testPurchaseTicketResponse: PurchaseTicketResponse = {
    dateOfPurchase: new Date(),
    id: 0,
    price: TicketPricingType.REGULAR,
    ticket: {
      id: 0,
      normalPrice: 1,
      reducedPrice: 0.5,
      name: "Test ticket",
      ticketType: TicketType.SINGLE,
    },
    ticketPurchaseStatus: PurchaseTicketStatus.INACTIVE
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(PurchaseTicketService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('purchaseTicket()', () => {
    it('should call endpoint using POST with proper body', () => {
      service.purchaseTicket(testPurchaseTicketRequest).subscribe();

      const httpRequest = httpMock.expectOne(service.apiURL);
      expect(httpRequest.request.method).toBe("POST");
      expect(httpRequest.request.body).toBe(testPurchaseTicketRequest);
      httpRequest.flush(testPurchaseTicketResponse);
    });
  });

  describe('getPurchasedTickets()', () => {
    it('should call endpoint using GET', () => {
      service.getPurchasedTickets().subscribe();

      const httpRequest = httpMock.expectOne(service.apiURL);
      expect(httpRequest.request.method).toBe("GET");
      httpRequest.flush([testPurchaseTicketResponse]);
    });
  });

  describe('getPurchasedTicketForVerificationCode()', () => {
    it('should call verification endpoint using GET', () => {
      service.getPurchasedTicketForVerificationCode('1').subscribe();

      const httpRequest = httpMock.expectOne(`${service.apiURL}/1`);
      expect(httpRequest.request.method).toBe("GET");
      httpRequest.flush(testPurchaseTicketResponse);
    });
  });
});
