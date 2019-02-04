import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { CarService } from '../../services/car.service';
import { Router } from '@angular/router';
import { CarReservationComponent } from '../../car-reservation/car-reservation/car-reservation.component';
import { CarReservationDialogComponent } from '../../car-reservation/car-reservation-dialog/car-reservation-dialog.component';

@Component({
  selector: 'car-list',
  templateUrl: './car-list.component.html',
  styleUrls: ['./car-list.component.css']
})
export class CarListComponent implements OnInit {

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

  @Input()
  get filter(){
    return this._filter;
  }
  set filter(filter:any){
    if(!filter)return;
    this._filter = {}
    Object.assign(this._filter, filter);
    this.carService.getAllCarsByRentACar(this.rentACarId,this._filter).subscribe(
      data =>{ 
        this.cars = data
        this.createTableElements();
      }
    );
  }
  _filter:any;
  cars:any;
  displayedColumns = ['name', 'model_brand','type', 'year', 'seats', 'price','rating', 'id'];
  dataSource:MatTableDataSource<any>;

  constructor(private carService:CarService,
              public dialog: MatDialog,
              private router:Router) { }

  ngOnInit() {
    if(this.isReservation) return;
    this.carService.getAllCarsByRentACar(this.rentACarId).subscribe(
      data =>{ 
        this.cars = data
        this.createTableElements();
      }
    );
  }

  createTableElements(){
    const cars = this.cars.map(car =>{ 
      switch(car.carType){
      case 'PASSENGER_CAR': car.carType = 'Passenger'; break;
      case 'FREIGHT_CAR': car.carType = 'Freight'; break;
      }
      car.rating = [
        {name:'Rating', value:car.averageRating},
        {name:'Not rating', value:5-car.averageRating}
      ]
      return car;
    });
    this.dataSource = new MatTableDataSource<any>(cars);
  }

  deleteCar(carId){
    this.carService.deleteCar(this.rentACarId, carId).subscribe(()=>{
      const index =this.cars.findIndex(car => car.id === carId);
      this.cars.splice(index,1);
      this.createTableElements();
    });    
  }

  reserve(carId){
    const car = this.cars.find(c => c.id === carId);
    this.dialog.open(CarReservationDialogComponent, {
      data : {
        car : car,
        reservationInfo : this.filter
      },
      width: "50%"
    })
  } 

}
