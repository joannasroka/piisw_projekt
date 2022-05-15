import { TestBed } from '@angular/core/testing';

import { VerificationTokenService } from './verification-token.service';

describe('VerificationTokenService', () => {
  let service: VerificationTokenService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VerificationTokenService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
