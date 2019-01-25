import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { CarReservationService } from '../../services/car-reservation.service';

@Component({
  selector: 'car-reservation-history',
  templateUrl: './car-reservation-history.component.html',
  styleUrls: ['./car-reservation-history.component.css']
})
export class CarReservationHistoryComponent implements OnInit {
 
  reservations:any;
  displayedColumns = ['car', 'date','place','id'];
  dataSource:MatTableDataSource<any>;

  constructor(private reservationService:CarReservationService) { }

  ngOnInit() {
   
    this.reservationService.getReservationHistory().subscribe(data =>{ 
        this.reservations = data
        this.createTableElements();
    });
  }

  createTableElements(){
    this.dataSource = new MatTableDataSource<any>(this.reservations);
  }


  cancel(reservationId){
    this.reservationService.cancelReservation(reservationId).subscribe(
      data => {},
      error =>{}
    );
  }

  canCancel(reservationId){
    return true;
  }

}
