import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseTicketsComponent } from './browse-tickets.component';
import { RouterTestingModule } from "@angular/router/testing";
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { FormsModule } from '@angular/forms';
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('BrowseTicketsComponent', () => {
  let component: BrowseTicketsComponent;
  let fixture: ComponentFixture<BrowseTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatToolbarModule,
        MatButtonModule,
        MatButtonToggleModule,
        FormsModule,
        HttpClientTestingModule
      ],
      declarations: [ BrowseTicketsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseTicketsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
