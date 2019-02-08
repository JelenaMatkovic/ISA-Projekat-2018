import { Component, OnInit } from '@angular/core';
import { AvioCompanyService } from '../../services/avio-company.service';
import { Router, ActivatedRoute } from '@angular/router';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';

@Component({
  selector: 'app-fast-reservation-flight',
  templateUrl: './fast-reservation-flight.component.html',
  styleUrls: ['./fast-reservation-flight.component.css']
})
export class FastReservationFlightComponent implements OnInit {

  avioCompanyId:any;
  reservations:any;

  constructor(private _avioComapnyService : AvioCompanyService,
    private router:Router,
    private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
                      
    this.activatedRoute.paramMap.pipe(delay(350)).subscribe(
      params => {
          this.avioCompanyId = params.get('id');
          this._avioComapnyService.getFastReservationOfAvio(this.avioCompanyId).subscribe(
              data => {
                        this.reservations = data;
              }
          );
      });
  }

  rezervisi(id:any){
    this._avioComapnyService.addFastReservationOfAvio(id).subscribe(
          data =>{
            this.router.navigateByUrl("profile");
    });
  }

}
