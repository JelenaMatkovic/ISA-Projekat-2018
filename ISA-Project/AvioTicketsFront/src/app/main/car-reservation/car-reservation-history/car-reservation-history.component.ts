import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatSnackBar, MatDialog } from '@angular/material';
import { CarReservationService } from '../../services/car-reservation.service';
import * as moment from 'moment';
import { RatingDialogComponent } from '../../rating/rating-dialog/rating-dialog.component';
import { RatingService } from '../../services/rating.service';

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
              private snackBar:MatSnackBar,
              private dialog:MatDialog,
              private ratingService:RatingService) { }

  ngOnInit() {
    this.fetchData();
  }

  fetchData(){
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

  rateCar(reservationId){
    const reservation = this.reservations.find(r => r.id === reservationId);
    this.dialog.open(RatingDialogComponent, {
        data : {
          name : reservation.carName
        },
        width: "50%"
    }).afterClosed().subscribe(result =>{
      if(!result)return
      result.reservationId = reservationId;
      this.ratingService.rateCar(reservation.carId, result).subscribe(()=>{
        this.fetchData();
      });
    });

  }

  rateRentACar(reservationId){
    const reservation = this.reservations.find(r => r.id === reservationId);
    this.dialog.open(RatingDialogComponent, {
        data : {
          name : reservation.rentACarName
        },
        width: "50%"
    }).afterClosed().subscribe(result =>{
      if(!result)return
      result.reservationId = reservationId;
      this.ratingService.rateRentACar(reservation.rentACarId, result).subscribe(()=>{
        this.fetchData();
      });
    });

  }

  canCancel(reservationId){
    const reservation = this.reservations.find( r => r.id === reservationId);
    const date = moment(reservation.dateTake);
    date.subtract(2 , 'days');
    return moment().isSameOrBefore(date);  
  }

}
