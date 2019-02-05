import { Component, OnInit } from '@angular/core';
import { RentACarService } from '../../services/rent-a-car.service';
import { MatTableDataSource, Sort } from '@angular/material';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import * as moment from 'moment';
import { AuthService } from '../../services/auth.service';
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

  canAdd:boolean;
  canReserve:boolean;

  constructor(private rentACarService:RentACarService,
              private formBuilder:FormBuilder,
              private authService:AuthService,
              private router:Router) { }

  ngOnInit() {
    this.canAdd = this.authService.getUserType() == 'SUPER_ADMIN';
    this.canReserve = this.authService.getUserType() == 'USER';
    this.searchForm = this.formBuilder.group({
      name:[''],
      location:[''],
      dateTake:[null],
      dateReturn:[null]
    });
    this.fetchData();
  }

  fetchData(){
    const search = this.searchForm.getRawValue();
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

  sortData(sort:Sort){
    const sortedRentACars = this.rentACars.sort((a,b) =>{
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'name': return compare(a.name.toLowerCase(), b.name.toLowerCase(), isAsc);
        case 'address': return compare(a.address.toLowerCase(), b.address.toLowerCase(), isAsc);
        default: return 0;
      }
    })
    this.dataSource =  new MatTableDataSource<any>(sortedRentACars);
  }


}

function compare(a: number | string, b: number | string, isAsc: boolean) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
