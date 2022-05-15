import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SimpleInfoDialogComponent } from './simple-info-dialog.component';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from "@angular/material/dialog";

describe('SimpleInfoDialogComponent', () => {
  let component: SimpleInfoDialogComponent;
  let fixture: ComponentFixture<SimpleInfoDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ MatDialogModule ],
      declarations: [ SimpleInfoDialogComponent ],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MatDialogRef, useValue: {} },
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleInfoDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
