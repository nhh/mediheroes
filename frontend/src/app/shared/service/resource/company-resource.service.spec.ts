import { TestBed, inject } from '@angular/core/testing';

import { CompanyResourceService } from './company-resource.service';

describe('CompanyResourceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CompanyResourceService]
    });
  });

  it('should be created', inject([CompanyResourceService], (service: CompanyResourceService) => {
    expect(service).toBeTruthy();
  }));
});
