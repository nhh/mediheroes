import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { JobOfferOverviewComponent } from './job-offer-overview.component';

describe('JobOfferOverviewComponent', () => {
  let component: JobOfferOverviewComponent;
  let fixture: ComponentFixture<JobOfferOverviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ JobOfferOverviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JobOfferOverviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
