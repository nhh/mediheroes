import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeJobOffersComponent } from './employee-job-offers.component';

describe('EmployeeJobOffersComponent', () => {
  let component: EmployeeJobOffersComponent;
  let fixture: ComponentFixture<EmployeeJobOffersComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeJobOffersComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeJobOffersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
