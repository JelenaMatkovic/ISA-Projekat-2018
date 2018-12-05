import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, ValidatorFn, ValidationErrors } from '@angular/forms';
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
      confirmPassword:['',Validators.required],
      firstName:['', Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]+')])],
      lastName:['',  Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]+')])],
      telephone:['', Validators.pattern('[0-9]*')],
      city:['', Validators.pattern('[a-zA-Z ]*')]
    }, { validator: this.checkPasswords });
  }
  

  save(){
    const user = this.form.getRawValue();
    this.userService.register(user).subscribe(
      data=>{
        const snack = this.snackBar.open(
          "You successfully registered!",
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

  checkPasswords (group: FormGroup): ValidationErrors | null  {
    console.log('usao');
    const password = group.get('password').value;
    const confirmPassword = group.get('confirmPassword').value;
  
    const confirmPasswordControl = group.get('confirmPassword');
    if(password !== confirmPassword){
      
      confirmPasswordControl.setErrors({ 'password': true })
      console.log(confirmPasswordControl);
      return { 'password': true };
    }
    confirmPasswordControl.setErrors(null);
  };

}


