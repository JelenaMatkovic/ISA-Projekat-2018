import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import * as moment from 'moment';
import { CarReservationService } from '../../services/car-reservation.service';
@Component({
  selector: 'app-car-reservation-dialog',
  templateUrl: './car-reservation-dialog.component.html',
  styleUrls: ['./car-reservation-dialog.component.css']
})
export class CarReservationDialogComponent implements OnInit {


  totalPrice:number;
  dateTake:string='';
  dateReturn: string='';

  constructor(
    private reservationService: CarReservationService,
    public dialogRef: MatDialogRef<CarReservationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}
  
  ngOnInit() {
    const dateTake = moment(this.data.reservationInfo.dateTake);
    this.dateTake = dateTake.format("DD-MM-YYYYTHH:mm:ss");
    const dateReturn = moment(this.data.reservationInfo.dateReturn);
    this.dateReturn = dateReturn.format("DD-MM-YYYYTHH:mm:ss");
    const  duration = moment.duration(dateReturn.diff(dateTake));
    const days = duration.days();
    console.log(days);
    this.totalPrice = days * this.data.car.price;

  }

  confirm(){
    const reservation = {
      carId : this.data.car.id,
      dateTake : this.data.reservationInfo.dateTake,
      dateReturn : this.data.reservationInfo.dateReturn,
      placeTake : this.data.reservationInfo.placeTake,
      placeReturn : this.data.reservationInfo.placeReturn,
      
    };
    console.log(this.reservationService);
    this.reservationService.createReservation(reservation).subscribe(
      data => this.dialogRef.close()
    )
    
  }

  cancel(){
    this.dialogRef.close();
  }

 

}
