import { TestBed } from '@angular/core/testing';

import { PassengerService } from './passenger.service';
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";

describe('PassengerService', () => {
  let service: PassengerService;
  let httpMock: HttpTestingController;
  let testPassengerSignupRequest = {
    email: "test@mail.com",
    firstName: "John",
    lastName: "Smith"
  }
  let testPassengerSignupResponse = {
    id: 1,
    email: "test@mail.com",
    firstName: "John",
    lastName: "Smith"
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });
    service = TestBed.inject(PassengerService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('signup()', () => {
    it('should call endpoint using POST', () => {
      service.signup(testPassengerSignupRequest).subscribe();

      const httpRequest = httpMock.expectOne(`${service.apiURL}`);
      expect(httpRequest.request.method).toBe("POST");
      expect(httpRequest.request.body).toBe(testPassengerSignupRequest);
      httpRequest.flush(testPassengerSignupResponse);
    });
  });
});
