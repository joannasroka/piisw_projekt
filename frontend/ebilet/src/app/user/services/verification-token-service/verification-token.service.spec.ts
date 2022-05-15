import { TestBed } from '@angular/core/testing';

import { VerificationTokenService } from './verification-token.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('VerificationTokenService', () => {
  let service: VerificationTokenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
    });
    service = TestBed.inject(VerificationTokenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
