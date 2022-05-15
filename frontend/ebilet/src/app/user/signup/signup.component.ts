import { AfterContentChecked, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  Validators
} from "@angular/forms";
import { PassengerSingupRequest } from "../models/request/passengerSingupRequest";
import { ActivatedRoute, Router } from "@angular/router";
import { SnackbarService } from "../../shared/snackbar/snackbar-service/snackbar.service";
import { AuthenticationService } from "../../authentication/services/authentication-service/authentication.service";
import { PassengerService } from '../services/passenger-service/passenger.service';
import { BackendError } from "../../shared/models/BackendError";
import { DialogService } from "../../shared/dialog/dialog-service/dialog.service";
import { checkEmailMismatch, CrossFieldErrorMatcher } from "../../helpers/formControlMatching/formControlMatching";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent implements OnInit, AfterContentChecked {
  signupForm: FormGroup;
  passengerSingupRequest: PassengerSingupRequest | undefined;
  errorMatcher = new CrossFieldErrorMatcher();
  loading = false;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private snackbarService: SnackbarService,
    private dialogService: DialogService,
    private authenticationService: AuthenticationService,
    private passengerService: PassengerService,
    private cdRef: ChangeDetectorRef
  ) {
    this.signupForm = this.formBuilder.group({
      email: ['', Validators.compose([Validators.required, Validators.email])],
      repeatEmail: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required]
    }, {validators: [checkEmailMismatch]});
  }

  ngOnInit(): void {
  }

  ngAfterContentChecked(): void {
    this.cdRef.detectChanges();
  }

  get f(): { [p: string]: AbstractControl } {
    return this.signupForm.controls;
  }
  onSubmit(): void {
    // stop here if form is invalid
    if (this.signupForm.invalid) {
      return;
    }

    this.loading = true;

    this.passengerSingupRequest = {
      email: this.f['email'].value,
      firstName: this.f['firstName'].value,
      lastName: this.f['lastName'].value,
    };

    this.passengerService.signup(this.passengerSingupRequest)
      .subscribe({
        next: () => {
          this.loading = false;
          this.dialogService.openInfoDialog('Signup completed successfully',
            'You now have to <b>verify</b> your e-mail address and set up your password, by clicking an <b>activation link</b> in an activation e-mail.', true);
        },
        error: err => {
          const backendError : BackendError = err.error
          console.log(backendError)
          if (backendError.globalException.message === 'error.failedValidation' && backendError.validationErrors[0].message == "error.emailExists") {
            this.snackbarService.openErrorSnackbar('Account with provided email address already exists');
            this.f['email'].setErrors({ emailAlreadyUsed: true });
          } else {
            this.snackbarService.openErrorSnackbar('There was an error during signup');
          }
          this.loading = false;
        }
      });
  }

}
