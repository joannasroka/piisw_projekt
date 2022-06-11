import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MyTicketsComponent } from './my-tickets.component';
import { RouterTestingModule } from "@angular/router/testing";
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';

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
        MatButtonToggleModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
