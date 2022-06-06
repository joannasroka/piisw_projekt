import { CurrencyPipe } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatStepperModule } from '@angular/material/stepper';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';
import { SharedModule } from 'src/app/shared/shared.module';

import { BuyTicketComponent } from './buy-ticket.component';
import { MatIconModule } from "@angular/material/icon";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { ActivatedRoute } from "@angular/router";
import { TicketType } from "../models/ticketType";
import { TicketPricingType } from "../models/ticketPricingType";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

describe('BuyTicketComponent', () => {
  let component: BuyTicketComponent;
  let fixture: ComponentFixture<BuyTicketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BuyTicketComponent ],
      imports: [
        RouterTestingModule,
        SharedModule,
        MatToolbarModule,
        MatStepperModule,
        MatIconModule,
        HttpClientTestingModule,
        BrowserAnimationsModule
      ],
      providers: [
        CurrencyPipe,
        {
          provide: ActivatedRoute,
          useValue: {snapshot:
              {queryParamMap: {get: () => TicketPricingType.REGULAR},
                data: {ticketToBuy: {
                  id: 1,
                  normalPrice: 10,
                  reducedPrice: 10,
                  name: "Test ticket",
                  ticketType: TicketType.SINGLE
              }}}}
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BuyTicketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
