import { ErrorStateMatcher } from "@angular/material/core";
import { FormControl, FormGroup, FormGroupDirective, NgForm, ValidationErrors } from "@angular/forms";

export class CrossFieldErrorMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    return !!(control && form && control.dirty && form.invalid);
  }
}

export const checkEmailMismatch = (formGroup: FormGroup): ValidationErrors | null => {
  const email = formGroup.get('email');
  const repeatEmail = formGroup.get('repeatEmail');
  return email && repeatEmail && email.value !== repeatEmail.value ? { emailMismatch: true } : null;
};

export const checkPasswordsMismatch = (control: FormGroup): ValidationErrors | null => {
  const password = control.get('password');
  const repeatPassword = control.get('repeatPassword');
  return password && repeatPassword && password.value !== repeatPassword.value ? { passwordMismatch: true } : null;
};
