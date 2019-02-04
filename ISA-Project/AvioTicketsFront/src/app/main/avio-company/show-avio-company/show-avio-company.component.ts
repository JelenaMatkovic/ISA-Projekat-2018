import { Component, OnInit, Input } from '@angular/core';
import { AvioCompanyService } from '../../services/avio-company.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ShowAvioCompaniesComponent } from '../show-avio-companies/show-avio-companies.component';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';

@Component({
  selector: 'app-show-avio-company',
  templateUrl: './show-avio-company.component.html',
  styleUrls: ['./show-avio-company.component.css']
})
export class ShowAvioCompanyComponent implements OnInit {

  avioCompanyId : any;
  avioCompany : any;
  destinations : any;
  flights : any;
  temp: number;

  constructor(private _avioComapnyService : AvioCompanyService,
              private router:Router,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
                      
    this.activatedRoute.paramMap.pipe(delay(350)).subscribe(
      params => {
          this.avioCompanyId = params.get('id');
          this._avioComapnyService.getAvioCompanyById(this.avioCompanyId).subscribe(
            data => this.avioCompany = data
          );
          this._avioComapnyService.getDestination(this.avioCompanyId).subscribe(
            data => this.destinations = data
          );
          this._avioComapnyService.getFlights(this.avioCompanyId).subscribe(
            data =>{
             this.flights = data
             console.log(this.flights)
          }
          );
          
      }
    );
    
    
      
  }

  deleteDestination(id:number,i:number){
    this._avioComapnyService.deleteDestination(this.avioCompanyId,id).subscribe(

    );
    this.destinations.splice(i,1);
  }

}
