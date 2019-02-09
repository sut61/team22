import { TestBed, inject } from '@angular/core/testing';

import { CancleService } from './cancle.service';

describe('CancleService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CancleService]
    });
  });

  it('should be created', inject([CancleService], (service: CancleService) => {
    expect(service).toBeTruthy();
  }));
});
