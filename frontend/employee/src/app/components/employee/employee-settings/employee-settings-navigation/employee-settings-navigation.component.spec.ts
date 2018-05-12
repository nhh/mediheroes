import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeSettingsNavigationComponent } from './employee-settings-navigation.component';

describe('EmployeeSettingsNavigationComponent', () => {
  let component: EmployeeSettingsNavigationComponent;
  let fixture: ComponentFixture<EmployeeSettingsNavigationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeSettingsNavigationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeSettingsNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
