import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InspectTicketsComponent } from './inspect-tickets.component';
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { SharedModule } from 'src/app/shared/shared.module';
import { CurrencyPipe } from '@angular/common';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatIconModule } from "@angular/material/icon";
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

describe('InspectTicketsComponent', () => {
  let component: InspectTicketsComponent;
  let fixture: ComponentFixture<InspectTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InspectTicketsComponent ],
      imports: [
        ReactiveFormsModule,
        HttpClientTestingModule,
        MatToolbarModule,
        MatFormFieldModule,
        MatInputModule,
        BrowserAnimationsModule,
        MatIconModule,
        SharedModule
      ],
      providers: [
        CurrencyPipe,
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InspectTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
