import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';

import { LoginComponent } from './login.component';
import { RouterTestingModule } from "@angular/router/testing";
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { HttpClientTestingModule } from "@angular/common/http/testing";
import { SharedModule } from 'src/app/shared/shared.module';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { of, throwError } from "rxjs";
import { AuthenticationService } from "../services/authentication-service/authentication.service";
import { Router } from '@angular/router';
import { SnackbarService } from "../../shared/snackbar/snackbar-service/snackbar.service";

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let authenticationServiceMock = jasmine.createSpyObj('authenticationService', {
    login: of({})
  });
  let snackbarServiceMock = jasmine.createSpyObj('snackbarService', {
    openSuccessSnackbar: {},
    openErrorSnackbar: {}
  })

  let router: Router;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ReactiveFormsModule,
        RouterTestingModule,
        MatSnackBarModule,
        HttpClientTestingModule,
        SharedModule,
        MatCardModule,
        MatFormFieldModule,
        MatInputModule,
        BrowserAnimationsModule
      ],
      declarations: [ LoginComponent ],
      providers: [
        {provide: AuthenticationService, useValue: authenticationServiceMock},
        {provide: SnackbarService, useValue: snackbarServiceMock}
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    router = TestBed.inject(Router);
    spyOn(router, 'navigate').and.returnValue(Promise.resolve(true));
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  describe('onSubmit()', () => {
    beforeEach( () => {
      authenticationServiceMock.login.calls.reset();
      snackbarServiceMock.openSuccessSnackbar.calls.reset();
      snackbarServiceMock.openErrorSnackbar.calls.reset();
    })

    it('should not attempt login if form is invalid (empty)', () => {
      component.onSubmit();
      expect(authenticationServiceMock.login).not.toHaveBeenCalled();
    });

    it('should not attempt login if form is invalid (only email is provided)', () => {
      component.loginForm.controls['email'].setValue("test@mail.com");
      component.onSubmit();

      expect(authenticationServiceMock.login).not.toHaveBeenCalled();
    });

    it('should not attempt login if form is invalid (only password is provided)', () => {
      component.loginForm.controls['password'].setValue("password");
      component.onSubmit();

      expect(authenticationServiceMock.login).not.toHaveBeenCalled();
    });

    it('should not attempt login if form is invalid (wrong emails is provided)', () => {
      component.loginForm.controls['email'].setValue("testmail.com");
      component.onSubmit();

      expect(authenticationServiceMock.login).not.toHaveBeenCalled();
    });

    it('should call authenticationService.login if form is valid', () => {
      component.loginForm.controls['email'].setValue("test@mail.com");
      component.loginForm.controls['password'].setValue("password");
      component.onSubmit();

      expect(authenticationServiceMock.login).toHaveBeenCalled();
    });

    it('should display success snackbar after successful login',  async () => {
      component.loginForm.controls['email'].setValue("test@mail.com");
      component.loginForm.controls['password'].setValue("password");
      component.onSubmit();

      expect(await snackbarServiceMock.openSuccessSnackbar).toHaveBeenCalled();
    });

    it('should navigate after successful login',  () => {
      component.loginForm.controls['email'].setValue("test@mail.com");
      component.loginForm.controls['password'].setValue("password");
      component.onSubmit();

      expect(router.navigate).toHaveBeenCalled();
    });

    it('should reset login form after unsuccessful login', () => {
      authenticationServiceMock.login.and.returnValue(throwError(() => null));
      spyOn(component.loginForm,'reset');

      component.loginForm.controls['email'].setValue("test@mail.com");
      component.loginForm.controls['password'].setValue("password");
      component.onSubmit();

      expect(component.loginForm.reset).toHaveBeenCalled();
    });

    it('should display error snackbar after unsuccessful login', () => {
      authenticationServiceMock.login.and.returnValue(throwError(() => null));

      component.loginForm.controls['email'].setValue("test@mail.com");
      component.loginForm.controls['password'].setValue("password");
      component.onSubmit();

      expect(snackbarServiceMock.openErrorSnackbar).toHaveBeenCalled();
    });
  });
});
