import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user:any;

  constructor(private authService:AuthService,
              private userService:UserService) { }

  ngOnInit() {

    this.userService.getLoged().subscribe(
      data =>{
                this.user = data;
                console.log(this.user);
      });
  }

}
