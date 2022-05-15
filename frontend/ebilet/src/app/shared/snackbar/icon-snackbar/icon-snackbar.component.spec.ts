import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconSnackbarComponent } from './icon-snackbar.component';
import { MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { MatIconModule } from '@angular/material/icon';

describe('IconSnackbarComponent', () => {
  let component: IconSnackbarComponent;
  let fixture: ComponentFixture<IconSnackbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ MatIconModule ],
      declarations: [ IconSnackbarComponent ],
      providers: [
        { provide: MAT_DIALOG_DATA, useValue: {} },
        { provide: MAT_SNACK_BAR_DATA, useValue: {} }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(IconSnackbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
