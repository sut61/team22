import { TestBed, inject } from '@angular/core/testing';

import { CheckproductService } from './checkproduct.service';

describe('CheckService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CheckproductService]
    });
  });

  it('should be created', inject([CheckproductService], (service: CheckproductService) => {
    expect(service).toBeTruthy();
  }));
});
