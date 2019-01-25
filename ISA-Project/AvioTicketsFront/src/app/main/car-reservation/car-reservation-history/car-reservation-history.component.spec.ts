import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CarReservationHistoryComponent } from './car-reservation-history.component';

describe('CarReservationHistoryComponent', () => {
  let component: CarReservationHistoryComponent;
  let fixture: ComponentFixture<CarReservationHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CarReservationHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CarReservationHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
