import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form:FormGroup;
  
  constructor(private formBuilder:FormBuilder,
              private userService:UserService,
              public snackBar: MatSnackBar,
              private router:Router) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      email:['', Validators.compose([Validators.required, Validators.email])],
      password:['',Validators.required],
      firstName:['', Validators.required],
      lastName:['', Validators.required],
      telephone:[''],
      city:['']
    });
  }
  

  save(){
    const user = this.form.getRawValue();
    this.userService.register(user).subscribe(
      data=>{
        const snack = this.snackBar.open(
          "You successfully login in!",
          "Close",
          {duration:2000, verticalPosition: "top"}
        );
        snack.afterDismissed().subscribe(()=>{
          this.router.navigateByUrl('/login');
        });      
      },
      error =>{
        const snack = this.snackBar.open(
          "Registration failed!",
          "Close",
          {duration:3000, verticalPosition: "top"}
        );
      });
  }

}
