import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { RentACarService } from '../../services/rent-a-car.service';
import { Router, ActivatedRoute } from '@angular/router';
import { BranchService } from '../../services/branch.service';

@Component({
  selector: 'app-add-branch',
  templateUrl: './add-branch.component.html',
  styleUrls: ['./add-branch.component.css']
})
export class AddBranchComponent implements OnInit {

  form:FormGroup
  rentACarId:number;
  constructor(private formBuilder:FormBuilder,
              public snackBar:MatSnackBar,
              private branchService:BranchService,
              private router:Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params =>{
      this.rentACarId = params.rentACarId;
    })
    this.form = this.formBuilder.group({
      address:['',Validators.required]
    });
  }

  addBranch(){
    const branch= this.form.getRawValue();
    this.branchService.addBranch(this.rentACarId,branch).subscribe(
      data=>{
          this.router.navigateByUrl('/rent-a-car/' + this.rentACarId);      
      } 
    );
  }

}
