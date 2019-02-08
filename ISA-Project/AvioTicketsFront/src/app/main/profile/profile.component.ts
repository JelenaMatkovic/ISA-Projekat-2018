import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { AvioCompanyService } from '../services/avio-company.service';
import { forEach } from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user:any;
  searchedFlights:any;
  temp:any;
  tempD:any = 0;
  thisB:boolean = true;

  constructor(private authService:AuthService,
              private avioService:AvioCompanyService,
              private userService:UserService) { }

  ngOnInit() {

    this.userService.getLoged().subscribe(
      data =>{
                this.user = data;
                console.log(this.user);
      });
    this.avioService.getAllReservationOfUser().subscribe(
      data=>{
                this.searchedFlights = data;
                for (let index = 0; index < this.searchedFlights.length; index++) {
                  this.temp = Date.parse(this.searchedFlights[index].dateAndTimeEnd) 
                          - Date.parse(this.searchedFlights[index].dateAndTimeStart);
                  this.searchedFlights[index].timeOfTravel = Math.round(this.temp / 1000 / 60);
                }
      }
    )

  }

  otkazi(id:any){
          this.avioService.removeReservation(id).subscribe(
            data =>{
              
                
          });
          this.searchedFlights.forEach(element => {
            if(element.id == id && this.thisB){
              this.searchedFlights.splice(this.tempD,1);
              this.thisB = false;
            }
            this.tempD++;
          });
          this.tempD = 0;
          this.thisB = true;
  }

}
