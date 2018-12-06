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

  constructor(private rentACarService:RentACarService,
              private carService:CarService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      params => this.rentACarId = params.rentACarId 
    );

    this.rentACarService.getRentACarById(this.rentACarId).subscribe(
      data => this.rentACar = data
    );

    this.carService.getAllCarsByRentACar(this.rentACarId).subscribe(
      data => this.cars = data
    );

  }

}
