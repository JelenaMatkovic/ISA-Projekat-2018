import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BranchService } from '../../services/branch.service';

@Component({
  selector: 'app-car-reservation',
  templateUrl: './car-reservation.component.html',
  styleUrls: ['./car-reservation.component.css']
})
export class CarReservationComponent implements OnInit {

  rentACarId:number;
  form:FormGroup;
  branches:any;
  
  constructor(private activatedRoute:ActivatedRoute,
              private branchService:BranchService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      dateTake:[null],
      dateReturn:[null],
      placeTake:[null],
      placeReturn:[null],
      type:[null],
      passengers:[null],
      priceStart:[null],
      priceEnd:[null]
    });
    this.activatedRoute.params.subscribe( params =>
      this.rentACarId = params.rentACarId
    );

    this.branchService.getAllBranchesByRentACar(this.rentACarId).subscribe( data =>
      this.branches = data
    );
  }

  find(){

  }

}
