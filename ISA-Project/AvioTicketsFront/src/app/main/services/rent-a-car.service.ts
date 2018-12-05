import { Injectable } from '@angular/core';
import { SERVER_URL } from '../main.constant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RentACarService {

  constructor(private http:HttpClient, private authService:AuthService) { }

  addRentACar(rentACar){
    return this.http.post(SERVER_URL + '/rent-a-car',rentACar );
  }

}
