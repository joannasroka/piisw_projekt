import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { MatSnackBarModule } from "@angular/material/snack-bar";
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MatMenuModule } from "@angular/material/menu";
import { SharedModule } from './shared/shared.module';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from "@angular/material/toolbar";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { AccountStatus } from "./authentication/models/accountStatus";
import { UserRole } from "./authentication/models/userRole";
import { UserAuth } from "./authentication/models/userAuth";

describe('AppComponent', () => {
  let app: AppComponent;
  let fixture: ComponentFixture<AppComponent>;

  let testPassengerUser: UserAuth;
  let testInspectorUser: UserAuth;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        MatSnackBarModule,
        HttpClientTestingModule,
        MatMenuModule,
        SharedModule,
        MatSidenavModule,
        MatToolbarModule,
        BrowserAnimationsModule,
        MatIconModule,
        MatListModule
      ],
      declarations: [
        AppComponent
      ]
    }).compileComponents();

    testPassengerUser = {
      accountStatus: AccountStatus.ACTIVE,
      email: "test@example.com",
      firstName: "John",
      id: "1",
      lastName: "Smith",
      role: UserRole.PASSENGER
    }

    testInspectorUser = {
      accountStatus: AccountStatus.ACTIVE,
      email: "test@example.com",
      firstName: "John",
      id: "1",
      lastName: "Smith",
      role: UserRole.INSPECTOR
    }

    let store = {} as any;
    const mockSessionStorage = {
      getItem: (key: string): string => {
        return key in store ? store[key] : null;
      },
      setItem: (key: string, value: string) => {
        store[key] = `${value}`;
      },
      removeItem: (key: string) => {
        delete store[key];
      },
      clear: () => {
        store = {};
      }
    };

    spyOn(sessionStorage, 'getItem')
      .and.callFake(mockSessionStorage.getItem);
    spyOn(sessionStorage, 'setItem')
      .and.callFake(mockSessionStorage.setItem);
    spyOn(sessionStorage, 'removeItem')
      .and.callFake(mockSessionStorage.removeItem);
    spyOn(sessionStorage, 'clear')
      .and.callFake(mockSessionStorage.clear);
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppComponent);
    app = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the app', () => {
    expect(app).toBeTruthy();
  });

  it('#isUserRolePassenger should return proper value', () => {
  });
});
