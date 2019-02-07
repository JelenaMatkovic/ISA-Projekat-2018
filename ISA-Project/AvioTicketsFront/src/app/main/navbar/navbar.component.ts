import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';

@Component({
  selector: 'navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user:any;

  constructor(private authService:AuthService,
              private userService:UserService) { }

  ngOnInit() {
    console.log(this.authService.getUserType());

    this.userService.getLoged().subscribe(
      data =>{
                this.user = data;
    });
  }

  logout(){
    localStorage.clear();
  }

}
