import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { RentACarService } from '../../services/rent-a-car.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-rent-a-car',
  templateUrl: '../add-rent-a-car/add-rent-a-car.component.html',
  styleUrls: ['../add-rent-a-car/add-rent-a-car.component.css']
})
export class EditRentACarComponent implements OnInit {

  header='Update Rent-a-Car';
  form:FormGroup;
  rentACar:any;
  rentACarId:number;

  constructor(private formBuilder:FormBuilder,
    private rentACarService:RentACarService,
    private activatedRoute:ActivatedRoute,
    private router:Router) { }

  ngOnInit() {

    this.activatedRoute.params.subscribe(params =>{
      this.rentACarId = params.rentACarId;
    });
    
    this.form = this.formBuilder.group({
      name:['',Validators.required],
      address:['',Validators.required],
      description:['']
    });

    this.rentACarService.getRentACarById(this.rentACarId).subscribe(
      data => {
        this.rentACar = data
        this.setValues();
      }
    );
  }

  setValues(){
    this.form.controls.name.setValue(this.rentACar.name);
    this.form.controls.address.setValue(this.rentACar.address);
    this.form.controls.description.setValue(this.rentACar.description);
  }

  saveRentACar(){
    const rentACar= this.form.getRawValue();
    this.rentACarService.updateRentACar(this.rentACarId, rentACar).subscribe(
      data=>{
          this.router.navigateByUrl('/rent-a-car/'+ this.rentACarId);      
      } 
    );
  }
}
