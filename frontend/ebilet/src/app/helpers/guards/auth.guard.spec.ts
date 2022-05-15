import { TestBed } from '@angular/core/testing';

import { AuthGuard } from './auth.guard';
import { RouterTestingModule } from "@angular/router/testing";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('AuthGuard', () => {
  let guard: AuthGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatSnackBarModule,
        HttpClientTestingModule
      ]
    });
    guard = TestBed.inject(AuthGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
