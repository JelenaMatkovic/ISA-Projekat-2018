import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { Router } from '@angular/router';
import { CarQuickTicketService } from '../../services/car-quick-ticket.service';
import { CarReservationDialogComponent } from '../../car-reservation/car-reservation-dialog/car-reservation-dialog.component';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'car-quick-ticket-list',
  templateUrl: './car-quick-ticket-list.component.html',
  styleUrls: ['./car-quick-ticket-list.component.css']
})
export class CarQuickTicketListComponent implements OnInit {

  @Input()rentACarId:number;

  @Input()
  get isReservation(){
    return this._isReservation;
  }
  set isReservation(isReservation:boolean){
    console.log(isReservation)
    this._isReservation = isReservation;
  }
  _isReservation:boolean=false;

 
  tickets:any;
  displayedColumns = ['car', 'date','place'];
  dataSource:MatTableDataSource<any>;

  constructor(private ticketService:CarQuickTicketService,
              private carService:CarService,
              public dialog: MatDialog,
              private router:Router) { }

  ngOnInit() {
    if(this.isReservation){
      this.displayedColumns.push('id');
      this.ticketService.getTickets().subscribe(data =>{ 
          this.tickets = data
          this.createTableElements();
      });
      return;
    }
    this.ticketService.getTicketsByRentACar(this.rentACarId).subscribe(data =>{ 
        this.tickets = data
        this.createTableElements();
    });
  }

  createTableElements(){
    this.dataSource = new MatTableDataSource<any>(this.tickets);
  }


  reserve(ticketId){
    const ticketOriginal = this.tickets.find(t => t.id === ticketId);
    const ticket:any = {}
    Object.assign(ticket, ticketOriginal);
    ticket.branchTake = { address: ticket.placeTakeAddress}
    ticket.branchReturn = { address: ticket.placeReturnAddress}
    this.carService.getCarById(ticket.rentACarId,ticket.carId).subscribe(data =>{
      this.dialog.open(CarReservationDialogComponent, {
        data : {
          car : data,
          reservationInfo : ticket,
          isQuick : true
        },
        width: "50%"
      }).afterClosed().subscribe(result=>this.ngOnInit());
    });
  } 
}
