import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseTicketRowComponent } from './purchase-ticket-row.component';
import { CurrencyPipe } from "@angular/common";
import { MatExpansionModule } from "@angular/material/expansion";
import { SharedModule } from 'src/app/shared/shared.module';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { TicketPricingType } from "../models/ticketPricingType";
import { PurchaseTicketStatus } from "../models/purchaseTicketStatus";
import { TicketType } from "../models/ticketType";
import { MatIconModule } from '@angular/material/icon';

describe('PurchaseTicketRowComponent', () => {
  let component: PurchaseTicketRowComponent;
  let fixture: ComponentFixture<PurchaseTicketRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseTicketRowComponent ],
      imports: [
        MatExpansionModule,
        SharedModule,
        BrowserAnimationsModule,
        MatIconModule
      ],
      providers: [
        CurrencyPipe,
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseTicketRowComponent);
    component = fixture.componentInstance;
    component.purchasedTicket = {
      globalId: "1",
      ticket: {
        globalId: "1",
        normalPrice: 1,
        reducedPrice: 1,
        name: "Test ticket",
        ticketType: TicketType.SINGLE
      },
      price: TicketPricingType.REGULAR,
      dateOfPurchase: new Date(),
      ticketPurchaseStatus: PurchaseTicketStatus.INACTIVE,
    }
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
