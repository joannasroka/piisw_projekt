import { TestBed } from '@angular/core/testing';

import { NotAuthGuard } from './not-auth.guard';
import { RouterTestingModule } from "@angular/router/testing";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('NotAuthGuard', () => {
  let guard: NotAuthGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatSnackBarModule,
        HttpClientTestingModule
      ]
    });
    guard = TestBed.inject(NotAuthGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
