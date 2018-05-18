import { TestBed, inject } from '@angular/core/testing';

import { JobOfferResourceService } from './job-offer-resource.service';

describe('JobOfferResourceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [JobOfferResourceService]
    });
  });

  it('should be created', inject([JobOfferResourceService], (service: JobOfferResourceService) => {
    expect(service).toBeTruthy();
  }));
});
