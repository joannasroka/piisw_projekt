import { TestBed } from '@angular/core/testing';

import { PassengerService } from './passenger.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('PassengerService', () => {
  let service: PassengerService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ]
    });
    service = TestBed.inject(PassengerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
