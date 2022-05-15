import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirmSignupComponent } from './confirm-signup.component';
import { ReactiveFormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

describe('ConfirmSignupComponent', () => {
  let component: ConfirmSignupComponent;
  let fixture: ComponentFixture<ConfirmSignupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        RouterTestingModule,
        MatSnackBarModule,
        MatDialogModule,
        HttpClientTestingModule,
        SharedModule,
        MatCardModule,
        BrowserAnimationsModule
      ],
      declarations: [ ConfirmSignupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ConfirmSignupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
