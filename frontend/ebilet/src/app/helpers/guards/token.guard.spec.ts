import { TestBed } from '@angular/core/testing';

import { TokenGuard } from './token.guard';
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { RouterTestingModule } from "@angular/router/testing";
import { MatSnackBarModule } from '@angular/material/snack-bar';

describe('TokenGuard', () => {
  let guard: TokenGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule,
        RouterTestingModule,
        MatSnackBarModule
      ]
    });
    guard = TestBed.inject(TokenGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
