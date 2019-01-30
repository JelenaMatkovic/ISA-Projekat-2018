import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AvioCompanyService } from '../../services/avio-company.service';
import { Router, ActivatedRoute } from '@angular/router';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';

@Component({
  selector: 'app-update-destination',
  templateUrl: './update-destination.component.html',
  styleUrls: ['./update-destination.component.css']
})
export class UpdateDestinationComponent implements OnInit {
  
  updateDestinationForm:FormGroup;
  submitted = false;
  destination : any;
  destinationId : any;
  avioCompanyId: any;

  constructor(private _avioComapnyService : AvioCompanyService,
    private formBuilder:FormBuilder,
    private router:Router,
    private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      params => {
          this.destinationId = params.get('idDes');
          this.avioCompanyId = params.get('id');
          
      }
    );
    this._avioComapnyService.getDestinationById(this.destinationId).subscribe(
      data => this.destination = data
    );

    this.updateDestinationForm = this.formBuilder.group({
      name:['',Validators.required],
      description:['',Validators.required],
    });
  }

  get f() { return this.updateDestinationForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.updateDestinationForm.invalid) {
        return;
    }

    this._avioComapnyService. updateDestination(this.destination).pipe(delay(300)).subscribe(
      data =>
              this.router.navigateByUrl("avio-company/showAvioCompany/" + this.avioCompanyId)
    );


  }

}
