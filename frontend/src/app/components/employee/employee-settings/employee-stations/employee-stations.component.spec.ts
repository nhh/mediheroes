import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeStationsComponent } from './employee-stations.component';

describe('EmployeeStationsComponent', () => {
  let component: EmployeeStationsComponent;
  let fixture: ComponentFixture<EmployeeStationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmployeeStationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmployeeStationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
