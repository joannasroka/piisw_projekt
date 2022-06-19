import { TestBed } from '@angular/core/testing';

import { VerificationTokenService } from './verification-token.service';
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";

describe('VerificationTokenService', () => {
  let service: VerificationTokenService;
  let httpMock: HttpTestingController;
  let testVerifyTokenRequest = {
    token: "token",
    password: "password"
  }
  let testPassengerSignupResponse = {
    id: 1,
    email: "test@mail.com",
    firstName: "Jonh",
    lastName: "Smith"
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
    });
    service = TestBed.inject(VerificationTokenService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('checkTokenValidity()', () => {
    it('should call endpoint using HEAD', () => {
      service.checkTokenValidity('token').subscribe();

      const httpRequest = httpMock.expectOne(`${service.apiURL}?token=token`);
      expect(httpRequest.request.method).toBe("HEAD");
      httpRequest.flush(null);
    });
  });

  describe('verifyToken()', () => {
    it('should call endpoint using POST with proper body', () => {
      service.verifyToken(testVerifyTokenRequest).subscribe();

      const httpRequest = httpMock.expectOne(`${service.apiURL}`);
      expect(httpRequest.request.method).toBe("POST");
      expect(httpRequest.request.body).toBe(testVerifyTokenRequest);
      httpRequest.flush(testPassengerSignupResponse);
    });
  });
});
