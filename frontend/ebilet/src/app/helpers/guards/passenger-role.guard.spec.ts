import { TestBed } from '@angular/core/testing';

import { PassengerRoleGuard } from './passenger-role.guard';
import { RouterTestingModule } from "@angular/router/testing";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PassengerRoleGuard', () => {
  let guard: PassengerRoleGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatSnackBarModule,
        HttpClientTestingModule
      ]
    });
    guard = TestBed.inject(PassengerRoleGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
