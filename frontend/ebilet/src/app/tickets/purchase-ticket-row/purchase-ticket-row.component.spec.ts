import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseTicketRowComponent } from './purchase-ticket-row.component';
import { CurrencyPipe } from "@angular/common";
import { MatExpansionModule } from "@angular/material/expansion";
import { SharedModule } from 'src/app/shared/shared.module';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

describe('PurchaseTicketRowComponent', () => {
  let component: PurchaseTicketRowComponent;
  let fixture: ComponentFixture<PurchaseTicketRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PurchaseTicketRowComponent ],
      imports: [
        MatExpansionModule,
        SharedModule,
        BrowserAnimationsModule
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
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
