import { TestBed, inject } from '@angular/core/testing';

import { RestoreService } from './restore.service';


describe('RestoreService', () => {
    beforeEach(() => {
      TestBed.configureTestingModule({
        providers: [RestoreService]
      });
    });
  
    it('should be created', inject([RestoreService], (service: RestoreService) => {
      expect(service).toBeTruthy();
    }));
  });
  