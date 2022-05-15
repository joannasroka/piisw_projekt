import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseTicketsComponent } from './browse-tickets.component';

describe('BrowseTicketsComponent', () => {
  let component: BrowseTicketsComponent;
  let fixture: ComponentFixture<BrowseTicketsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
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
