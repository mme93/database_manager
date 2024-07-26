import { TestBed } from '@angular/core/testing';

import { TableDialogServiceService } from './table-dialog-service.service';

describe('TableDialogServiceService', () => {
  let service: TableDialogServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TableDialogServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
