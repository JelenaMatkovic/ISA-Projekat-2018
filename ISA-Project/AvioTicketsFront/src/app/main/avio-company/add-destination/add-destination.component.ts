import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AvioCompanyService } from '../../services/avio-company.service';
import { Router, ActivatedRoute } from '@angular/router';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';

@Component({
  selector: 'app-add-destination',
  templateUrl: './add-destination.component.html',
  styleUrls: ['./add-destination.component.css']
})
export class AddDestinationComponent implements OnInit {

  addDestinationForm:FormGroup;
  submitted = false;
  destination: any;
  avioCompanyId: any;

  constructor(private _avioComapnyService : AvioCompanyService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.activatedRoute.paramMap.subscribe(
      params => {
          this.avioCompanyId = params.get('id');
          
      }
    );

    this.addDestinationForm = this.formBuilder.group({
      description:['',Validators.required],
      nameOfAirPort:['',Validators.required],
      nameOfCountry:['',Validators.required],
      nameOfTown:['',Validators.required]
    });


  }
  get f() { return this.addDestinationForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.destination = this.addDestinationForm.getRawValue()
    // stop here if form is invalid
    if (this.addDestinationForm.invalid) {
        return;
    }
    this._avioComapnyService. addDestination(this.avioCompanyId,this.destination).pipe(delay(300)).subscribe(
      data =>
              this.router.navigateByUrl("avio-company/showAvioCompany/" + this.avioCompanyId)
    );

    


  }

}
