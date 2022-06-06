import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoadingSpinnerComponent } from './loading-spinner/loading-spinner.component';
import { MatProgressSpinnerModule } from "@angular/material/progress-spinner";
import { IconSnackbarComponent } from './snackbar/icon-snackbar/icon-snackbar.component';
import { MatIconModule } from "@angular/material/icon";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { SimpleInfoDialogComponent } from './dialog/simple-info-dialog/simple-info-dialog.component';
import { MatDialogModule } from "@angular/material/dialog";
import { MatButtonModule } from "@angular/material/button";
import { PasswordInputComponent } from './password-input/password-input.component';
import { MatPasswordStrengthModule } from "@angular-material-extensions/password-strength";
import { MatFormFieldModule } from "@angular/material/form-field";
import { ReactiveFormsModule } from "@angular/forms";
import { MatInputModule } from "@angular/material/input";
import { DetailsTableComponent } from './details-table/details-table.component';
import { MatTableModule } from "@angular/material/table";
import { YesNoDialogComponent } from './dialog/yes-no-dialog/yes-no-dialog.component';

@NgModule({
  declarations: [
    LoadingSpinnerComponent,
    IconSnackbarComponent,
    SimpleInfoDialogComponent,
    PasswordInputComponent,
    DetailsTableComponent,
    YesNoDialogComponent
  ],
  exports: [
    LoadingSpinnerComponent,
    PasswordInputComponent,
    DetailsTableComponent
  ],
    imports: [
        CommonModule,
        MatProgressSpinnerModule,
        MatIconModule,
        MatSnackBarModule,
        MatDialogModule,
        MatButtonModule,
        MatPasswordStrengthModule,
        MatFormFieldModule,
        ReactiveFormsModule,
        MatInputModule,
        MatTableModule
    ]
})
export class SharedModule { }
