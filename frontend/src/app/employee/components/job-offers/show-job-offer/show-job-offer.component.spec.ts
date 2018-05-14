import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowJobOfferComponent } from './show-job-offer.component';

describe('ShowJobOfferComponent', () => {
  let component: ShowJobOfferComponent;
  let fixture: ComponentFixture<ShowJobOfferComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowJobOfferComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowJobOfferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
