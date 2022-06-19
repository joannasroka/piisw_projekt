import { TestBed } from '@angular/core/testing';

import { SnackbarService } from './snackbar.service';
import { MatSnackBar } from '@angular/material/snack-bar';

describe('SnackbarService', () => {
  let service: SnackbarService;
  let matSnackBarMock = jasmine.createSpyObj("MatSnackBar", {
    openFromComponent: null
  });

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {provide: MatSnackBar, useValue: matSnackBarMock}
      ]
    });
    service = TestBed.inject(SnackbarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('openSuccessSnackbar()', () => {
    it('should execute openFromComponent method of MatSnackBar', () => {
      service.openSuccessSnackbar('test');
      expect(matSnackBarMock.openFromComponent).toHaveBeenCalled();
    });
  });

  describe('openErrorSnackbar()', () => {
    it('should execute openFromComponent method of MatSnackBar', () => {
      service.openErrorSnackbar('test');
      expect(matSnackBarMock.openFromComponent).toHaveBeenCalled();
    });
  });
});
