import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef, MatSnackBar } from '@angular/material';
import * as moment from 'moment';
import { CarReservationService } from '../../services/car-reservation.service';
import { CarQuickTicketService } from '../../services/car-quick-ticket.service';
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
    public snackBar: MatSnackBar,
    private ticketService:CarQuickTicketService,
    public dialogRef: MatDialogRef<CarReservationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}
  
  ngOnInit() {
    const dateTake = moment(this.data.reservationInfo.dateTake);
    this.dateTake = dateTake.format("DD-MM-YYYYTHH:mm:ss");
    const dateReturn = moment(this.data.reservationInfo.dateReturn);
    this.dateReturn = dateReturn.format("DD-MM-YYYYTHH:mm:ss");
    if(this.data.isQuick){
      this.totalPrice = this.data.reservationInfo.totalPrice;
    }else{    
      const  duration = moment.duration(dateReturn.diff(dateTake));
      const days = duration.days();
      console.log(days);
      this.totalPrice = days * this.data.car.price;
    }

  }

  confirm(){
    if(this.data.isQuick){
      this.ticketService.reserveTicket(this.data.reservationInfo.id).subscribe(
        data => {
          this.dialogRef.close()
          this.snackBar.open(
            "You successfully reserved car!",
            "Close",
            {duration:2000, verticalPosition: "top"}
          );
        },
        error =>{
         this.snackBar.open(
            "There was a error while reserving car!",
            "Close",
            {duration:2000, verticalPosition: "top"}
          );
        }
         
      );
     
    }else {
      const reservation = {
        carId : this.data.car.id,
        dateTake : this.data.reservationInfo.dateTake,
        dateReturn : this.data.reservationInfo.dateReturn,
        placeTake : this.data.reservationInfo.placeTake,
        placeReturn : this.data.reservationInfo.placeReturn,
        
      };
      this.reservationService.createReservation(reservation).subscribe(
        data => {
          this.dialogRef.close()
          this.snackBar.open(
            "You successfully reserved car!",
            "Close",
            {duration:2000, verticalPosition: "top"}
          );
        },
        error => {
          this.snackBar.open(
            "There was a error while reserving car!",
            "Close",
            {duration:2000, verticalPosition: "top"}
          );
        }
      );
    }
    
  }

  cancel(){
    this.dialogRef.close();
  }

}
