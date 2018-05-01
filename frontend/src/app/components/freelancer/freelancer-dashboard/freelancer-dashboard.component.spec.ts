import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FreelancerDashboardComponent } from './freelancer-dashboard.component';

describe('FreelancerDashboardComponent', () => {
  let component: FreelancerDashboardComponent;
  let fixture: ComponentFixture<FreelancerDashboardComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FreelancerDashboardComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FreelancerDashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
