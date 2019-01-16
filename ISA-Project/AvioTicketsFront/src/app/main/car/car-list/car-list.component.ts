import { Component, OnInit, Input } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { CarService } from '../../services/car.service';
import { Router } from '@angular/router';

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
    this._filter = filter;
    this.carService.getAllCarsByRentACar(this.rentACarId,filter).subscribe(
      data =>{ 
        this.cars = data
        this.createTableElements();
      }
    );
  }
  _filter:any;
  cars:any;
  displayedColumns = ['name', 'model_brand','type', 'year', 'seats', 'price', 'id'];
  dataSource:MatTableDataSource<any>;

  constructor(private carService:CarService,
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
      return car;
    });
    this.dataSource = new MatTableDataSource<any>(cars);
  }

  deleteCar(carId){
    this.carService.deleteCar(this.rentACarId, carId).subscribe(()=>{
      const index =this.cars.findIndex(car => car.id === carId);
      this.cars.splice(index,1);
      this.createTableElements();
    })
    
  }

}
