import { TestBed } from '@angular/core/testing';

import { TicketPuncherService } from './ticket-puncher.service';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('TicketPuncherService', () => {
  let service: TicketPuncherService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientTestingModule ],
    });
    service = TestBed.inject(TicketPuncherService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
