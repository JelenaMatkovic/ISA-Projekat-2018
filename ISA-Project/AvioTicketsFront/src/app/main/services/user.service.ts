import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  register(user){
    return this.http.post(SERVER_URL + '/user', user );
  }

  login(user){
    return this.http.post(SERVER_URL+'/user/login',user);
  }

  test(){
    return this.http.get(SERVER_URL+'/user/test');
  }
  
}
