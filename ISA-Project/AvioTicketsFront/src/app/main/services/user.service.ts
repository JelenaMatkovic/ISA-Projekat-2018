import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';
import { text } from '@angular/core/src/render3';

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

  activateUser(hash){
    return this.http.patch(SERVER_URL + '/user/activation/' + hash, {});
  }

  getLoged(){
    return this.http.get(SERVER_URL + '/user/loged');
  }

  updateUser(user:any){
    return this.http.put(SERVER_URL + '/user/update',user);
  }

  updatePasswordUser(user:any){
    return this.http.put(SERVER_URL + '/user/updatePassword',user);
  }
  
}
