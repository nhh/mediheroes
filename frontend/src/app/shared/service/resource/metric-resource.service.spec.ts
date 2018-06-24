import { TestBed, inject } from '@angular/core/testing';

import { MetricResourceService } from './metric-resource.service';

describe('MetricResourceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MetricResourceService]
    });
  });

  it('should be created', inject([MetricResourceService], (service: MetricResourceService) => {
    expect(service).toBeTruthy();
  }));
});
