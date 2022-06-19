import { TestBed } from '@angular/core/testing';

import { DialogService } from './dialog.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { of } from "rxjs";
import { SimpleInfoDialogComponent } from "../simple-info-dialog/simple-info-dialog.component";
import { Router } from "@angular/router";
import { YesNoDialogComponent } from "../yes-no-dialog/yes-no-dialog.component";

describe('DialogService', () => {
  let service: DialogService;
  let matDialogMock = jasmine.createSpyObj("MatDialog", {
    open: jasmine.createSpyObj("dialogRef", {
      afterClosed: of(true)
    })
  });
  let routerMock = jasmine.createSpyObj('router', {
    navigate: Promise
  })

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {provide: MatDialog, useValue: matDialogMock},
        {provide: Router, useValue: routerMock}
      ]
    });
    service = TestBed.inject(DialogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('openInfoDialog()', () => {
    it('should execute open method of MatDialog with SimpleInfoDialogComponent and proper config', () => {
      let expectedConfig = new MatDialogConfig();

      expectedConfig.disableClose = true;

      expectedConfig.data = {
        title: 'test',
        description: 'test',
        redirectAfterClose: true
      };

      service.openInfoDialog('test','test', true)
      expect(matDialogMock.open).toHaveBeenCalledWith(SimpleInfoDialogComponent, expectedConfig);
    });

    it('should redirect after close when shouldRedirectAfterClose is true', () => {
      service.openInfoDialog('test','test', true)
      expect(routerMock.navigate).toHaveBeenCalled();
    });
  });

  describe('openYesNoDialog()', () => {
    it('should execute open method of MatDialog with YesNoDialogComponent and proper config', () => {
      let expectedConfig = new MatDialogConfig();

      expectedConfig.disableClose = true;

      expectedConfig.data = {
        title: 'test',
        description: 'test',
      };

      service.openYesNoDialog('test','test')
      expect(matDialogMock.open).toHaveBeenCalledWith(YesNoDialogComponent, expectedConfig);
    });
  });
});
