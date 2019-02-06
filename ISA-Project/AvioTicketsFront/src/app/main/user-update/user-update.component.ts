import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  user:any;
  updateUserForm:any;
  submitted:any;

  constructor(private authService:AuthService,
              private userService:UserService,
              private formBuilder:FormBuilder,
              private router:Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.updateUserForm = this.formBuilder.group({
      firstName:[null],
      lastName:[null],
      city: [null],
      telephone: [null]
    });

    this.userService.getLoged().subscribe(
      data =>{
                this.user = data;
    });

  }

  get f() { return this.updateUserForm.controls; }

  
  onSubmit() {
    this.submitted = true;
    console.log(this.user);

    this.userService.updateUser(this.user).subscribe(
          data => {
            this.router.navigateByUrl("/profile")
          }         
    )

  }

}
