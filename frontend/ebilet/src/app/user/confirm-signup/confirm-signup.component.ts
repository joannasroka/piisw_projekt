import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from "@angular/forms";
import { checkPasswordsMismatch } from "../../helpers/formControlMatching/formControlMatching";
import { VerifyTokenRequest } from "../models/request/verifyTokenRequest";
import { ActivatedRoute, Router } from "@angular/router";
import { SnackbarService } from "../../shared/snackbar/snackbar-service/snackbar.service";
import { DialogService } from "../../shared/dialog/dialog-service/dialog.service";
import { VerificationTokenService } from '../services/verification-token-service/verification-token.service';

@Component({
  selector: 'app-confirm-signup',
  templateUrl: './confirm-signup.component.html',
  styleUrls: ['./confirm-signup.component.scss']
})
export class ConfirmSignupComponent implements OnInit {
  confirmSignupForm: FormGroup;
  verifyTokenRequest: VerifyTokenRequest | undefined;
  loading = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private snackbarService: SnackbarService,
    private dialogService: DialogService,
    private verificationTokenService: VerificationTokenService,
  ) {
    this.confirmSignupForm = this.formBuilder.group({
      password: ['', Validators.compose([
        Validators.required,
        Validators.minLength(8),
        Validators.maxLength(60),
        Validators.pattern(/[A-Z]/),
        Validators.pattern(/[a-z]/),
        Validators.pattern(/\d/),
        Validators.pattern(/[!"#$%&'()*+,-./:;<=>?@\[\]^_`{|}~]/)
      ])],
      repeatPassword: ['', Validators.required],
    }, {validators: [checkPasswordsMismatch]});
  }

  ngOnInit(): void {
  }

  get f(): { [p: string]: AbstractControl } {
    return this.confirmSignupForm.controls;
  }

  onSubmit(): void {
    // stop here if form is invalid
    if (this.confirmSignupForm.invalid) {
      return;
    }

    this.loading = true;

    this.verifyTokenRequest = {
      token: this.route.snapshot.queryParams['token'],
      password: this.f['password'].value,
    };

    this.verificationTokenService.verifyToken(this.verifyTokenRequest)
      .subscribe({
        next: () => {
          this.loading = false;
          this.dialogService.openInfoDialog('Your account has been successfully verified!',
            'You can now log in to your account.', true, "/login");
        },
        error: () => {
          this.snackbarService.openErrorSnackbar('There was an error during account verification');
          this.loading = false;
        }
      });
  }

}
