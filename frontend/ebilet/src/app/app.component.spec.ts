import { TestBed } from '@angular/core/testing';
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

describe('AppComponent', () => {
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
      ],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });
});
