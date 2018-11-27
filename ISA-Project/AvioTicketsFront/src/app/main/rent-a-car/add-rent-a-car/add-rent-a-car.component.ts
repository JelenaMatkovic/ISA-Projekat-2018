import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { RentACarService } from '../../services/rent-a-car.service';

@Component({
  selector: 'app-add-rent-a-car',
  templateUrl: './add-rent-a-car.component.html',
  styleUrls: ['./add-rent-a-car.component.css']
})
export class AddRentACarComponent implements OnInit {

  form:FormGroup

  constructor(private formBuilder:FormBuilder,
    public snackBar: MatSnackBar,
    private rentACarService:RentACarService,
    private router:Router) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      name:['',Validators.required],
      address:['',Validators.required],
      description:['']

    });
  }

  addRentACar(){
    const  rentACar= this.form.getRawValue();
    this.rentACarService.addRentACar(rentACar).subscribe(
      data=>{
          this.router.navigateByUrl('/');      
      } 
    );
  }
}
