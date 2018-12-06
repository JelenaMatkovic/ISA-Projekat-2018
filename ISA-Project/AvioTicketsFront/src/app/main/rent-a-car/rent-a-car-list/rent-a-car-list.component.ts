import { Component, OnInit } from '@angular/core';
import { RentACarService } from '../../services/rent-a-car.service';
import { MatTableDataSource } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-rent-a-car-list',
  templateUrl: './rent-a-car-list.component.html',
  styleUrls: ['./rent-a-car-list.component.css']
})
export class RentACarListComponent implements OnInit {

  rentACars:any;
  displayedColumns = ['name', 'address', 'description'];
  dataSource:MatTableDataSource<any>;

  constructor(private rentACarService:RentACarService,
              private router:Router) { }

  ngOnInit() {
    this.rentACarService.getAllRentACars().subscribe(
      data =>{ 
        this.rentACars = data
        this.createTableElements();
      }
    );
  }

  createTableElements(){
    this.dataSource = new MatTableDataSource<any>(this.rentACars)
  }

  
  openRentACar(rentACarId){
    this.router.navigateByUrl('/rent-a-car/' + rentACarId);
  }


}
