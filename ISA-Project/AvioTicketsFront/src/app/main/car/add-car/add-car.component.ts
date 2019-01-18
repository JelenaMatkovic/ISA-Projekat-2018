import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RentACarService } from '../../services/rent-a-car.service';
import { MatSnackBar } from '@angular/material';
import { Router, ActivatedRoute } from '@angular/router';
import { CarService } from '../../services/car.service';

@Component({
  selector: 'app-add-car',
  templateUrl: './add-car.component.html',
  styleUrls: ['./add-car.component.css']
})
export class AddCarComponent implements OnInit {

  form:FormGroup
  rentACarId:number;
  constructor(private formBuilder:FormBuilder,
              public snackBar:MatSnackBar,
              private carService:CarService,
              private router:Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params=>{
      this.rentACarId = params.rentACarId;
    })
    this.form = this.formBuilder.group({
      name:['',Validators.required],
      model:['',Validators.required],
      brand:['',Validators.required],
      year:['',Validators.compose([Validators.required, Validators.pattern('[0-9]{4}'),Validators.min(1885)])],
      price:['',Validators.compose([Validators.required, Validators.pattern('[0-9]+')])],
      seats:['',Validators.compose([Validators.required, Validators.pattern('[0-9]+')])],
      carType:[null, Validators.required]
    });
  }
  
  saveCar(){
    const car = this.form.getRawValue();
    this.carService.addCar(this.rentACarId, car).subscribe(
      data=>{
        this.router.navigateByUrl('/rent-a-car/' + this.rentACarId);
      }
    )
  }

}
