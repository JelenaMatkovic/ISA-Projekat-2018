import { Component, OnInit } from '@angular/core';
import { UserService } from '../services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-activation',
  templateUrl: './activation.component.html',
  styleUrls: ['./activation.component.css']
})
export class ActivationComponent implements OnInit {

  hash:string;
  isActivated;
  constructor(private userService:UserService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(
      params => this.hash = params.hash
    );
    this.userService.activateUser(this.hash).subscribe(
      data => this.isActivated = true,
      error => this.isActivated = false
    )
  }

}
