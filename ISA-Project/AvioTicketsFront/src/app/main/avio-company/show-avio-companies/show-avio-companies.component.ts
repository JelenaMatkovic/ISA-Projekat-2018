import { Component, OnInit} from '@angular/core';
import { AvioCompanyService } from '../../services/avio-company.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-show-avio-companies',
  templateUrl: './show-avio-companies.component.html',
  styleUrls: ['./show-avio-companies.component.css']
})
export class ShowAvioCompaniesComponent implements OnInit {

  public avioCompanies:any;

  constructor(private _avioComapnyService : AvioCompanyService,
              private router:Router,
              private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.getAvioCompanies();

  }

  getAvioCompanies(){
    this._avioComapnyService.getAvioCompanies()
        .subscribe(data => this.avioCompanies = data);
  }

  RowSelected(u:any){
    this.router.navigateByUrl("avio-company/showAvioCompany/" + u.id);
  }

}
