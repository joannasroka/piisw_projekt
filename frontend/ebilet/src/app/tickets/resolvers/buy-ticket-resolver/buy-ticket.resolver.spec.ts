import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { BuyTicketResolver } from './buy-ticket.resolver';

describe('BuyTicketResolver', () => {
  let resolver: BuyTicketResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    });
    resolver = TestBed.inject(BuyTicketResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
