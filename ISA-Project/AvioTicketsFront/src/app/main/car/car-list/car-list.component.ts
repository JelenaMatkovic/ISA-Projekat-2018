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
  cars:any;
  displayedColumns = ['name', 'model_brand','id'];
  dataSource:MatTableDataSource<any>;

  constructor(private carService:CarService,
              private router:Router) { }

  ngOnInit() {
    this.carService.getAllCarsByRentACar(this.rentACarId).subscribe(
      data =>{ 
        this.cars = data
        this.createTableElements();
      }
    );
  }

  createTableElements(){
    this.dataSource = new MatTableDataSource<any>(this.cars);
  }


}
