import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  saveToken(token){
    localStorage.setItem('token', token);
  }

  saveUserType(userType){
    localStorage.setItem('user_type', userType);
  }

  getUserType(){
    return localStorage.getItem('user_type');
  }

  getToken(){
    return localStorage.getItem('token');
  }
}
