
import { CarReservationDialogComponent } from '../../car-reservation/car-reservation-dialog/car-reservation-dialog.component';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material';
import { Inject, Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-rating-dialog',
  templateUrl: './rating-dialog.component.html',
  styleUrls: ['./rating-dialog.component.css']
})
export class RatingDialogComponent implements OnInit {

  form:FormGroup

  constructor(
    public dialogRef: MatDialogRef<CarReservationDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private formBuilder:FormBuilder,
    ) {}

  ngOnInit() {
    this.form = this.formBuilder.group({
      rating:['',Validators.compose([Validators.required,Validators.min(1),Validators.max(5)])],
      
    });
  }

  rate(){
    this.dialogRef.close(this.form.getRawValue());
  }

}
