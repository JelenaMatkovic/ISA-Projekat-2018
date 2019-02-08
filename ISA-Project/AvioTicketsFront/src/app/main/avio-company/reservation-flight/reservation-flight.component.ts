import { Component, OnInit, Inject, InjectionToken } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';
import { AvioCompanyService } from '../../services/avio-company.service';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-reservation-flight',
  templateUrl: './reservation-flight.component.html',
  styleUrls: ['./reservation-flight.component.css']
})
export class ReservationFlightComponent implements OnInit {
  avioFlightId : any;
  flight : any;
  temp: number;
  tempA: any[];
  tempB: any[];
  tempCount:any = 0;
  tempBol:boolean = true;
  numberOfReservation: number[] = [];
  classOfReservation: string[] = [];
  numberOfSeats: number;
  userForm:any;
  userArray: any[] = [];
  counter:number = 0;
  

  

  constructor(private avioService:AvioCompanyService,
              private router:Router,
              private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder) {
                var element = <HTMLInputElement> document.getElementById("5");
  }

  ngOnInit() {

    this.activatedRoute.paramMap.pipe(delay(350)).subscribe(
      params => {
        this.avioFlightId = params.get('id');
        this.avioService.getFlightById(this.avioFlightId).subscribe(
          data => {
              this.flight = data;
              this.temp = Date.parse(this.flight.dateAndTimeEnd) 
                    - Date.parse(this.flight.dateAndTimeStart);
              this.flight.timeOfTravel = Math.round(this.temp / 1000 / 60);
              this.tempA = new Array(this.flight.numberOfSegments) ;
              console.log(this.tempA)
              if(this.flight.ecconomic != null){
                this.tempCount += this.flight.ecconomic.numberOfSeats
              }
              if(this.flight.business != null){
                this.tempCount += this.flight.business.numberOfSeats
              }
              if(this.flight.first != null){
                this.tempCount += this.flight.first.numberOfSeats
              }
              this.tempB = new Array(this.tempCount) ;
              console.log(this.tempB)
            } 
        );
          
      }
    );

    this.userForm = this.formBuilder.group({
      firstName:[null],
      lastName:[null],
      passport: [null],
      numberOfSeats: [null],
      classType: [null]
    });

    

    
  }

  change(i:any){
    const el: HTMLElement = document.getElementById(i);
    el.style.backgroundColor = "black";
    this.numberOfReservation.forEach(element => {
        if(element == i){
          this.tempBol = false;
        }
    });
    if(this.tempBol){
      this.numberOfReservation.push(i);
      this.classOfReservation.push(el.className.slice(8,9))
    }
    this.tempBol = true;

    
  }

  next(){
    console.log(this.numberOfReservation);
    const el: HTMLElement = document.getElementById("Forma");
    if(this.numberOfReservation.length > 0){
        el.style.visibility = "visible";
        this.numberOfSeats = this.numberOfReservation[this.counter];
        console.log("USAO DA ISPISE "  + this.numberOfSeats)
    }
  }

  nextUser(){
    //do predposlednjeg
    this.userArray.push(this.userForm.getRawValue());
    this.userForm.reset();
    this.counter++;
    this.numberOfSeats = this.numberOfReservation[this.counter];
    console.log(this.userArray);
  }

  rezervacija(){
    //na kraju ...
    if(  this.userArray.length != this.numberOfReservation.length)
         this.userArray.push(this.userForm.getRawValue());
    for (let index = 0; index < this.numberOfReservation.length; index++) {
      this.userArray[index].numberOfSeats = this.numberOfReservation[index];
      if(this.classOfReservation[index] == "i")
        this.userArray[index].classType = "Ecconomic";
      if(this.classOfReservation[index] == "p")
        this.userArray[index].classType = "Biznis";
      if(this.classOfReservation[index] == "d")
        this.userArray[index].classType = "Prva";
    }
    this.avioService.addReservation(this.avioFlightId,this.userArray).subscribe(
      data =>{
                    this.router.navigateByUrl("profile");
      });
    
  }

  getNumber(num:any){
    return new Array(num);   
  }

}
