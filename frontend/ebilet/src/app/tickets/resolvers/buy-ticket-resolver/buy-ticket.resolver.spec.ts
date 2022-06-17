import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { SharedModule } from 'src/app/shared/shared.module';

import { BuyTicketResolver } from './buy-ticket.resolver';

describe('BuyTicketResolver', () => {
  let resolver: BuyTicketResolver;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        SharedModule
      ]
    });
    resolver = TestBed.inject(BuyTicketResolver);
  });

  it('should be created', () => {
    expect(resolver).toBeTruthy();
  });
});
