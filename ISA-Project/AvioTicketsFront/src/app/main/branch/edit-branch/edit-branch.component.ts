import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material';
import { BranchService } from '../../services/branch.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'edit-branch',
  templateUrl: '../add-branch/add-branch.component.html',
  styleUrls: ['../add-branch/add-branch.component.css']
})
export class EditBranchComponent implements OnInit {

  header="Update branch";
  form:FormGroup;
  branch:any;
  rentACarId:number;
  branchId:number;

  constructor(private formBuilder:FormBuilder,
              private snackBar:MatSnackBar,
              private branchService:BranchService,
              private router:Router,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params=>{
      this.rentACarId = params.rentACarId;
      this.branchId=params.branchId;
    })

    this.form = this.formBuilder.group({
      address:['',Validators.required],
    });

    this.branchService.getBranchById(this.rentACarId,this.branchId).subscribe(
      data => {
        this.branch = data
        this.setValues();
      }
    );
  }

  setValues(){
    this.form.controls.address.setValue(this.branch.address);
  }

  saveBranch(){
    const branch= this.form.getRawValue();
    this.branchService.updateBranch(this.rentACarId, this.branchId,branch).subscribe(
      data=>{
          this.router.navigateByUrl('/rent-a-car/'+ this.rentACarId);      
      } 
    );
  }
}
