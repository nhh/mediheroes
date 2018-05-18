import { TestBed, inject } from '@angular/core/testing';

import { AbstractResourceService } from './abstract-resource.service';

describe('AbstractResourceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AbstractResourceService]
    });
  });

  it('should be created', inject([AbstractResourceService], (service: AbstractResourceService) => {
    expect(service).toBeTruthy();
  }));
});
