import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeEmployeesComponent } from './employee-employees.component';

describe('EmployeeEmployeesComponent', () => {
  let component: EmployeeEmployeesComponent;
  let fixture: ComponentFixture<EmployeeEmployeesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeEmployeesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
