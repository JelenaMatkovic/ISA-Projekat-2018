import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  isUser:boolean;
  constructor(private authService:AuthService) { }

  ngOnInit() {
    this.isUser = this.authService.getUserType() == 'USER';
  }

}
