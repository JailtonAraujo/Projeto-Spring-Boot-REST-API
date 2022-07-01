import { TestBed } from '@angular/core/testing';

import { SecutityGuard } from './secutity.guard';

describe('SecutityGuard', () => {
  let guard: SecutityGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(SecutityGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
