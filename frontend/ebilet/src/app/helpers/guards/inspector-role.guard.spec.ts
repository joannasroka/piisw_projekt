import { TestBed } from '@angular/core/testing';

import { InspectorRoleGuard } from './inspector-role.guard';
import { RouterTestingModule } from "@angular/router/testing";
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { HttpClientTestingModule } from "@angular/common/http/testing";

describe('InspectorRoleGuard', () => {
  let guard: InspectorRoleGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatSnackBarModule,
        HttpClientTestingModule
      ]
    });
    guard = TestBed.inject(InspectorRoleGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
