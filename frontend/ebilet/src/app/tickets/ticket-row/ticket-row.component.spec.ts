import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatListModule } from '@angular/material/list';

import { TicketRowComponent } from './ticket-row.component';
import { MatIconModule } from "@angular/material/icon";

describe('TicketRowComponent', () => {
  let component: TicketRowComponent;
  let fixture: ComponentFixture<TicketRowComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        MatListModule,
        MatIconModule
      ],
      declarations: [ TicketRowComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketRowComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
