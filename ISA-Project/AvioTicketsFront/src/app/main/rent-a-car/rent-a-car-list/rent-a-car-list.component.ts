import { Component, OnInit } from '@angular/core';
import { RentACarService } from '../../services/rent-a-car.service';
import { MatTableDataSource } from '@angular/material';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import * as moment from 'moment';
@Component({
  selector: 'app-rent-a-car-list',
  templateUrl: './rent-a-car-list.component.html',
  styleUrls: ['./rent-a-car-list.component.css']
})
export class RentACarListComponent implements OnInit {

  rentACars:any;
  displayedColumns = ['name', 'address', 'description', 'id'];
  dataSource:MatTableDataSource<any>;

  searchForm:FormGroup;

  constructor(private rentACarService:RentACarService,
              private formBuilder:FormBuilder,
              private router:Router) { }

  ngOnInit() {
    this.searchForm = this.formBuilder.group({
      name:[''],
      location:[''],
      dateTake:[null],
      dateReturn:[null]
    });
    this.fetchData();
  }

  fetchData(){
    console.log("dkajfkd");
    const search = this.searchForm.getRawValue();
    console.log(search);
    if(search.dateTake){
        search.dateTake = search.dateTake.subtract(search.dateTake.utcOffset(), "hours")
          .format("YYYY-MM-DDTHH:mm:ss");
    }
    if(search.dateReturn){
      search.dateReturn = search.dateReturn.subtract(search.dateReturn.utcOffset(), "hours")
        .format("YYYY-MM-DDTHH:mm:ss");
    }
    this.rentACarService.getAllRentACars(search).subscribe(
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

  search(){
    this.fetchData();
  }


}
