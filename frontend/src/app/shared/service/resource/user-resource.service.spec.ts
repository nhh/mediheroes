import { TestBed, inject } from '@angular/core/testing';

import { UserResourceService } from './user-resource.service';

describe('UserResourceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserResourceService]
    });
  });

  it('should be created', inject([UserResourceService], (service: UserResourceService) => {
    expect(service).toBeTruthy();
  }));
});
