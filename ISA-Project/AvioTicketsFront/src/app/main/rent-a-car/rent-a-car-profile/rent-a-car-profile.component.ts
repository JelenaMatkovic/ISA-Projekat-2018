import { Component, OnInit } from '@angular/core';
import { RentACarService } from '../../services/rent-a-car.service';
import { ActivatedRoute } from '@angular/router';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-rent-a-car-profile',
  templateUrl: './rent-a-car-profile.component.html',
  styleUrls: ['./rent-a-car-profile.component.css']
})
export class RentACarProfileComponent implements OnInit {

  rentACarId:number;
  rentACar:any;
  branches:any;
  cars:any;

  ratingData = [];
  reservedCarData:any=[];
  granularity='MONTH';

  constructor(private rentACarService:RentACarService,
              private carService:CarService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      params => this.rentACarId = params.rentACarId 
    );

    this.rentACarService.getRentACarById(this.rentACarId).subscribe(
      (data:any) => {
        this.rentACar = data
        this.ratingData = [{name: "Rating" , value : data.averageRating} , {name: "Not rating" , value : 5-data.averageRating} ]
      }
    );

    this.carService.getAllCarsByRentACar(this.rentACarId).subscribe(
      data => this.cars = data
    );

    this.rentACarService.getReservedCarsStatistic(this.rentACarId, this.granularity).subscribe(
      data => this.reservedCarData = [{name:'Reserved cars', series: data}]
    );

  }

  granularityChange(){
    this.rentACarService.getReservedCarsStatistic(this.rentACarId, this.granularity).subscribe(
      data => this.reservedCarData = [{name:'Reserved cars', series: data}]
    );
  }

}
