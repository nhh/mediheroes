import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeLocationsComponent } from './employee-locations.component';

describe('EmployeeLocationsComponent', () => {
  let component: EmployeeLocationsComponent;
  let fixture: ComponentFixture<EmployeeLocationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeLocationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeLocationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
