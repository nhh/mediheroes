import { TestBed, async, inject } from '@angular/core/testing';

import { IsFreelancerGuard } from './is-freelancer.guard';

describe('IsFreelancerGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IsFreelancerGuard]
    });
  });

  it('should ...', inject([IsFreelancerGuard], (guard: IsFreelancerGuard) => {
    expect(guard).toBeTruthy();
  }));
});
