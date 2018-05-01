import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeGeneralComponent } from './employee-general.component';

describe('EmployeeGeneralComponent', () => {
  let component: EmployeeGeneralComponent;
  let fixture: ComponentFixture<EmployeeGeneralComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeGeneralComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeGeneralComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
