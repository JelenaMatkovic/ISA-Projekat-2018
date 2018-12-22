import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { Router, ActivatedRoute } from '@angular/router';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'edit-car',
  templateUrl: '../add-car/add-car.component.html',
  styleUrls: ['../add-car/add-car.component.css']
})
export class EditCarComponent implements OnInit {

  header='Update Car';
  form:FormGroup;
  car:any;
  rentACarId:number;
  carId:number;
  
  constructor(private formBuilder:FormBuilder,
              private carService:CarService,
              private router:Router,
              private snackBar:MatSnackBar,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params =>{
      this.rentACarId = params.rentACarId;
      this.carId=params.carId;
    });
    
    this.form = this.formBuilder.group({
      name:['',Validators.required],
      model:['',Validators.required],
      brand:['',Validators.required],
      year:['',Validators.compose([Validators.required, Validators.pattern('[0-9]{4}'),Validators.min(1885)])],
      price:['',Validators.compose([Validators.required, Validators.pattern('[0-9]+')])],
      seats:['',Validators.compose([Validators.required, Validators.pattern('[0-9]+')])]
    });

    this.carService.getCarById(this.rentACarId,this.carId).subscribe(
      data => {
        this.car = data
        this.setValues();
      }
    );
  }

  setValues(){
    this.form.controls.name.setValue(this.car.name);
    this.form.controls.model.setValue(this.car.model);
    this.form.controls.brand.setValue(this.car.brand);
    this.form.controls.year.setValue(this.car.year);
    this.form.controls.price.setValue(this.car.price);
    this.form.controls.seats.setValue(this.car.seats);
  }

  saveCar(){
    const car= this.form.getRawValue();
    this.carService.updateCar(this.rentACarId, this.carId,car).subscribe(
      data=>{
          this.router.navigateByUrl('/rent-a-car/'+ this.rentACarId);      
      } 
    );
  }
}
