import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form:FormGroup

  constructor(private formBuilder:FormBuilder,
    private userService:UserService,
    public snackBar: MatSnackBar,
    private router:Router) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      email:['', Validators.compose([Validators.required, Validators.email])],
      password:['',Validators.required]
    });
  }

  login(){
    const user = this.form.getRawValue();
    this.userService.login(user).subscribe(
      data=>{
        this.userService.test().subscribe(data=>console.log(data))
        const snack = this.snackBar.open(
          "You successfully login!",
          "Close",
          {duration:2000, verticalPosition: "top"}
        );
        snack.afterDismissed().subscribe(()=>{
          this.router.navigateByUrl('/');
        })      
      },
      error =>{
        const snack = this.snackBar.open(
          "Email or password are not valid!",
          "Close",
          {duration:3000, verticalPosition: "top"}
        );
      }
    );
  }
}