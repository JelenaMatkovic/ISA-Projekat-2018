import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';
import { MatSnackBar } from '@angular/material';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form:FormGroup

  constructor(private formBuilder:FormBuilder,
    private userService:UserService,
    private authService:AuthService,
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
      (data:any)=>{
        console.log(data);
        this.authService.saveToken(data.token);
        this.authService.saveUserType(data.userType);
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
