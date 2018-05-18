import { TestBed, inject } from '@angular/core/testing';

import { TokenResourceService } from './token-resource.service';

describe('TokenResourceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TokenResourceService]
    });
  });

  it('should be created', inject([TokenResourceService], (service: TokenResourceService) => {
    expect(service).toBeTruthy();
  }));
});
