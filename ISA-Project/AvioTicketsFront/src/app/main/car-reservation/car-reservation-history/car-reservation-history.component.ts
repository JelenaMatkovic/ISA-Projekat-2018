import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatSnackBar } from '@angular/material';
import { CarReservationService } from '../../services/car-reservation.service';
import * as moment from 'moment';

@Component({
  selector: 'car-reservation-history',
  templateUrl: './car-reservation-history.component.html',
  styleUrls: ['./car-reservation-history.component.css']
})
export class CarReservationHistoryComponent implements OnInit {
 
  reservations:any;
  displayedColumns = ['car', 'date','place','id'];
  dataSource:MatTableDataSource<any>;

  constructor(private reservationService:CarReservationService,
              private snackBar:MatSnackBar) { }

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
      data => {
        this.ngOnInit();
      },
      error =>{
        this.snackBar.open(
          "Cannot cancel reservation",
          "Close",
          {duration:3000, verticalPosition: "top"}
        );
      }
    );
  }

  canCancel(reservationId){
    const reservation = this.reservations.find( r => r.id === reservationId);
    const date = moment(reservation.dateTake);
    date.subtract(2 , 'days');
    return moment().isSameOrBefore(date);  
  }

}
