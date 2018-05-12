import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplicationUnavailableComponent } from './application-unavailable.component';

describe('ApplicationUnavailableComponent', () => {
  let component: ApplicationUnavailableComponent;
  let fixture: ComponentFixture<ApplicationUnavailableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ApplicationUnavailableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ApplicationUnavailableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
