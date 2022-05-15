import { Injectable } from '@angular/core';
import { Router } from "@angular/router";
import { MatDialog, MatDialogConfig } from "@angular/material/dialog";
import { SimpleInfoDialogComponent } from "../simple-info-dialog/simple-info-dialog.component";

@Injectable({
  providedIn: 'root'
})
export class DialogService {

  constructor(
    private router: Router,
    private matDialog: MatDialog,
  ) { }

  openInfoDialog(dialogTitle: string, dialogDescription: string, shouldRedirectAfterClose: boolean, pathAfterClose?: string): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;

    dialogConfig.data = {
      title: dialogTitle,
      description: dialogDescription,
      redirectAfterClose: shouldRedirectAfterClose
    };

    const dialogRef = this.matDialog.open(SimpleInfoDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(shouldRedirect => {
      if (shouldRedirect) {
        this.router.navigate([pathAfterClose ? pathAfterClose : '/']);
      }
    });
  }
}
