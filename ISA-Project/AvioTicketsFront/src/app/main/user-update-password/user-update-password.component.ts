import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { UserService } from '../services/user.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-update-password',
  templateUrl: './user-update-password.component.html',
  styleUrls: ['./user-update-password.component.css']
})
export class UserUpdatePasswordComponent implements OnInit {
  
  passwords:any;
  updateUserPasswordForm:any;
  submitted:any;

  constructor(private authService:AuthService,
              private userService:UserService,
              private formBuilder:FormBuilder,
              private router:Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {

    this.updateUserPasswordForm = this.formBuilder.group({
      oldPassword:[null],
      newPassword:[null]
    });
  }

  get f() { return this.updateUserPasswordForm.controls; }

  
  onSubmit() {
    this.passwords = this.updateUserPasswordForm.getRawValue()

    console.log(this.passwords);

    this.userService.updatePasswordUser(this.passwords).subscribe(
      data => this.router.navigateByUrl("/profile")
    );

  }

}
