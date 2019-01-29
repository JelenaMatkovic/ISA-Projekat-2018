import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AvioCompanyService } from '../../services/avio-company.service';
import { Router, ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-update-avio-company',
  templateUrl: './update-avio-company.component.html',
  styleUrls: ['./update-avio-company.component.css']
})
export class UpdateAvioCompanyComponent implements OnInit {

  updateAvioCompanyForm:FormGroup;
  submitted = false;
  avioCompany : any;
  avioCompanyId : any;

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
    this._avioComapnyService.getAvioCompanyById(this.avioCompanyId).subscribe(
      data => this.avioCompany = data
    );

    this.updateAvioCompanyForm = this.formBuilder.group({
      name:['',Validators.required],
      address:['', Validators.required],
      description:['',Validators.required],
      rating:['',[Validators.required, Validators.minLength(1), Validators.maxLength(2)]]
    });
  }

  get f() { return this.updateAvioCompanyForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.updateAvioCompanyForm.invalid) {
        return;
    }

    this._avioComapnyService.updateAvioCompany(this.avioCompany).subscribe(
                              data => this.avioCompany = data
    );

    this.router.navigateByUrl("avio-company/showAvioCompany/" + this.avioCompany.id);


}

}
