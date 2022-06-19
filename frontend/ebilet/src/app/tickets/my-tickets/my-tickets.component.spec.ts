import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyTicketsComponent } from './my-tickets.component';
import { RouterTestingModule } from "@angular/router/testing";
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { SharedModule } from "../../shared/shared.module";
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { TicketType } from "../models/ticketType";
import { TicketPricingType } from "../models/ticketPricingType";
import { PurchaseTicketStatus } from "../models/purchaseTicketStatus";
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';

describe('MyTicketsComponent', () => {
  let component: MyTicketsComponent;
  let fixture: ComponentFixture<MyTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MyTicketsComponent ],
      imports: [
        RouterTestingModule,
        MatToolbarModule,
        MatButtonModule,
        MatButtonToggleModule,
        SharedModule,
        HttpClientTestingModule,
        MatListModule,
        MatIconModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyTicketsComponent);
    component = fixture.componentInstance;
    component.myTickets = [
      {
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
        ticketPurchaseStatus: PurchaseTicketStatus.ACTIVE,
      }
    ]
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
