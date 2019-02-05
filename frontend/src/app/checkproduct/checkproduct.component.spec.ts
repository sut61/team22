import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckproductComponent } from './checkproduct.component';

describe('CheckproductComponent', () => {
  let component: CheckproductComponent;
  let fixture: ComponentFixture<CheckproductComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckproductComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
