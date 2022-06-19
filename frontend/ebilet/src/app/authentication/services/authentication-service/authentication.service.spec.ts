import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import { HttpClientTestingModule, HttpTestingController } from "@angular/common/http/testing";
import { UserAuth } from "../../models/userAuth";
import { AccountStatus } from "../../models/accountStatus";
import { UserRole } from "../../models/userRole";
import { environment } from "../../../../environments/environment";

describe('AuthenticationService', () => {
  let service: AuthenticationService;
  let httpMock: HttpTestingController;
  const testPassengerUser: UserAuth = {
    accountStatus: AccountStatus.ACTIVE,
    email: "test@example.com",
    firstName: "John",
    id: "1",
    lastName: "Smith",
    role: UserRole.PASSENGER
  }
  const testInspectorUser: UserAuth = {
    accountStatus: AccountStatus.ACTIVE,
    email: "test@example.com",
    firstName: "John",
    id: "1",
    lastName: "Smith",
    role: UserRole.INSPECTOR
  }


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(AuthenticationService);
    httpMock = TestBed.inject(HttpTestingController);

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

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('readStoredUserAuth()', () => {
    it('should set userAuthSubject with the value from sessionStorage', () => {
      sessionStorage.setItem(service.STORAGE_KEY, JSON.stringify(testPassengerUser))

      service.readStoredUserAuth();

      expect(service.userAuthValue()).toBeTruthy();
    });

    it('should not set userAuthSubject if the value is not present in sessionStorage', () => {
      service.readStoredUserAuth();

      expect(service.userAuthValue()).toBeFalsy();
    });
  })

  describe('isUserRolePassenger()', () => {
    it('should report proper user role', () => {
      sessionStorage.setItem(service.STORAGE_KEY, JSON.stringify(testPassengerUser))
      service.readStoredUserAuth();

      expect(service.isUserRolePassenger()).toBeTrue();

      sessionStorage.setItem(service.STORAGE_KEY, JSON.stringify(testInspectorUser))
      service.readStoredUserAuth();

      expect(service.isUserRolePassenger()).toBeFalse();
    });
  });

  describe('isUserRoleInspector()', () => {
    it('should report proper user role', () => {
      sessionStorage.setItem(service.STORAGE_KEY, JSON.stringify(testInspectorUser))
      service.readStoredUserAuth();

      expect(service.isUserRoleInspector()).toBeTrue();

      sessionStorage.setItem(service.STORAGE_KEY, JSON.stringify(testPassengerUser))
      service.readStoredUserAuth();

      expect(service.isUserRoleInspector()).toBeFalse();
    });
  });

  describe('login()', () => {
    it('should call login endpoint', () => {
      service.login('test','test').subscribe();

      const loginRequest = httpMock.match(`${environment.apiUrl}/login`);
      expect(loginRequest.length).toBe(1);
      expect(loginRequest[0].request.method).toBe("POST");
      loginRequest[0].flush(null);

      const getUserAuthRequest = httpMock.match(`${environment.apiUrl}/api/users/@me`);
      getUserAuthRequest[0].flush(null);
    });

    it('should call /api/users/@me endpoint', () => {
      service.login('test','test').subscribe();

      const loginRequest = httpMock.match(`${environment.apiUrl}/login`);
      loginRequest[0].flush(null);

      const getUserAuthRequest = httpMock.match(`${environment.apiUrl}/api/users/@me`);
      expect(getUserAuthRequest.length).toBe(1);
      expect(getUserAuthRequest[0].request.method).toBe("GET");
      getUserAuthRequest[0].flush(null);
    });

    it('should save received user data to UserAuth and SessionStorage', () => {
      service.login('test','test').subscribe();

      const loginRequest = httpMock.match(`${environment.apiUrl}/login`);
      loginRequest[0].flush(null);

      const getUserAuthRequest = httpMock.match(`${environment.apiUrl}/api/users/@me`);
      getUserAuthRequest[0].flush(testPassengerUser);

      expect(service.userAuthValue()).toBeTruthy();
      expect(sessionStorage.getItem(service.STORAGE_KEY)).toBeTruthy();
    });
  });

  describe('logout()', () => {
    it('should call logout endpoint', () => {
      service.logout().subscribe();

      const logoutRequest = httpMock.expectOne(`${environment.apiUrl}/logout`);
      expect(logoutRequest.request.method).toBe("POST");
      logoutRequest.flush(null);
    });

    it('should remove user data from UserAuth and SessionStorage', () => {
      service.logout().subscribe();

      const logoutRequest = httpMock.expectOne(`${environment.apiUrl}/logout`);
      logoutRequest.flush(null);

      expect(service.userAuthValue()).toBeFalsy();
      expect(sessionStorage.getItem(service.STORAGE_KEY)).toBeFalsy();
    });
  });

});
