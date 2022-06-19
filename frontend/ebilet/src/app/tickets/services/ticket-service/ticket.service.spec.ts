import { TestBed } from '@angular/core/testing';

import { TicketService } from './ticket.service';
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { TicketType } from "../../models/ticketType";
import { TicketResponse } from '../../models/response/ticketResponse';

describe('TicketService', () => {
  let service: TicketService;
  let httpMock: HttpTestingController;
  let testTicketResponse: TicketResponse = {
    globalId: "1",
      normalPrice: 1,
      reducedPrice: 0.5,
      name: "Test ticket",
      ticketType: TicketType.SINGLE,
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
    });
    service = TestBed.inject(TicketService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('getTickets()', () => {
    it('should call endpoint using GET', () => {
      service.getTickets().subscribe();

      const httpRequest = httpMock.expectOne(`${service.apiURL}`);
      expect(httpRequest.request.method).toBe("GET");
      httpRequest.flush([testTicketResponse]);
    });
  });

  describe('getTicketById()', () => {
    it('should call endpoint using GET with proper Id in URL', () => {
      service.getTicketById("1").subscribe();

      const httpRequest = httpMock.expectOne(`${service.apiURL}/1`);
      expect(httpRequest.request.method).toBe("GET");
      httpRequest.flush(testTicketResponse);
    });
  });
});
