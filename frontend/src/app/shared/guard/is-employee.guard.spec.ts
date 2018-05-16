import { TestBed, async, inject } from '@angular/core/testing';

import { IsEmployeeGuard } from './is-employee.guard';

describe('IsEmployeeGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IsEmployeeGuard]
    });
  });

  it('should ...', inject([IsEmployeeGuard], (guard: IsEmployeeGuard) => {
    expect(guard).toBeTruthy();
  }));
});
